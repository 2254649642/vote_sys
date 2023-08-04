package com.cq.edu.service.impl;

import com.cq.edu.dao.AccountMapper;
import com.cq.edu.dao.AdminMapper;
import com.cq.edu.pojo.Account;
import com.cq.edu.pojo.Result;
import com.cq.edu.service.AccountService;
import com.cq.edu.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private Result result;


    @Override
    public List<Account> selectAllChangePage(Integer page) {
        Integer pageCount = page * 10;
        return adminMapper.selectAllChangePage(pageCount);
    }

    /**
     * 更新用户信息
     *
     * @param user
     * @return
     */
    @Override
    public Result updateUsers(Account user) {
        //先判断之前密码
        Account users = accountMapper.selectAccount(user.getAccount());
        //若前后密码不一致，则需要进行加密
        if (!users.getPassword().equals(user.getPassword())) {
            users.setPassword(encoder.encode(user.getPassword()));
        }

        int update = adminMapper.updateUsersInfo(user);
        System.out.println("update = " + update);
        if (update != 1) {
            return Result.success(500, "更新失败！", null);
        } else {
            return Result.success(200, "更新成功！", null);
        }
    }

    @Override
    public Result addUsers(Account user) {
        user.setPassword(encoder.encode(user.getPassword()));
        adminMapper.insertUser(user);
        return Result.success(200, "新增成功！", null);
    }

    @Override
    public Result removeUsers(String account) {
        adminMapper.deleteUser(account);
        return Result.success(200, "删除成功！", null);
    }
}
