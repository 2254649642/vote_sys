package com.cq.edu.service.impl;

import com.cq.edu.dao.AccountMapper;
import com.cq.edu.pojo.Account;
import com.cq.edu.pojo.Authority;
import com.cq.edu.pojo.Result;
import com.cq.edu.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private Result result;


    @Override
    public int insertAccount(Account account) {
        return accountMapper.insertAccount(account);
    }

    @Override
    public List<Authority> selectAuths(String s) {
        return accountMapper.selectAuths(s);
    }

    @Override
    public List<Account> getAllUser() {
        return accountMapper.selectAllUser();
    }

    @Override
    public Account getAccount(String username) {
        return accountMapper.selectAccount(username);
    }

    /**
     * 判断是否已经注册使用
     *
     * @param username
     * @return
     */
    @Override
    public Result isRegistered(String username) {
        Account tmpUser = accountMapper.selectAccount(username);

        if (tmpUser != null) {
            result = Result.error(500, "该账号已被注册");
        } else {
            result = Result.success(200, "账号可以使用", null);
        }
        return result;
    }


    /**
     * 注册用户
     *
     * @param user
     * @return
     */
    @Override
    public Result addUsers(Account user) {

        System.out.println("user = " + user);
        user.setRole_id("3");
        result = isRegistered(user.getAccount());
        if (result.getCode() == 200) {
            user.setPassword(encoder.encode(user.getPassword()));
            accountMapper.insertAccount(user);
            result = Result.success(200, "注册成功", null);
            return result;
        } else {
            return result;
        }
    }

    /**
     * 修改密码
     *
     * @param user
     * @return
     */
    @Override
    public Result changePasswd(Account user) {
        //先判断之前密码
        Account users = accountMapper.selectAccount(user.getAccount());
        //若前后密码不一致，则需要进行加密
        if (!users.getPassword().equals(user.getPassword())) {
            users.setPassword(encoder.encode(user.getPassword()));
        }
        int update = accountMapper.updatePasswd(users);
        if (update != 1) {
            return Result.error(500, "更新失败！");
        } else {
            return Result.success(200, "更新成功！", null);
        }
    }

    @Override
    public Result changeUserInfor(Account user) {
        int update = accountMapper.updateUserInfor(user);
        if (update != 1) {
            return Result.error(500, "更新失败！");
        } else {
            return Result.success(200, "更新成功！", null);
        }
    }
}
