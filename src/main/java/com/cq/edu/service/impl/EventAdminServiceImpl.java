package com.cq.edu.service.impl;

import com.cq.edu.dao.AccountMapper;
import com.cq.edu.dao.EventAdminMapper;
import com.cq.edu.pojo.Account;
import com.cq.edu.pojo.Result;
import com.cq.edu.pojo.Singer;
import com.cq.edu.service.EventAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class EventAdminServiceImpl implements EventAdminService {
    @Autowired
    private EventAdminMapper eventAdminMapper;

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private Result result;

    @Override
    public List<Singer> selectAllChangePage(Integer page) {
        Integer pageCount = page * 10;
        return eventAdminMapper.selectAllChangePage(pageCount);
    }

    /**
     * 更新用户信息
     *
     * @param singer
     * @return
     */
    @Override
    public Result updateSinger(Singer singer) {
        int update = eventAdminMapper.updateSingerInfo(singer);
        System.out.println("update = " + update);
        if (update != 1) {
            return Result.success(500, "更新失败！", null);
        } else {
            return Result.success(200, "更新成功！", null);
        }
    }

    @Override
    public Result addSinger(Singer singer) {
        eventAdminMapper.insertSinger(singer);
        return Result.success(200, "新增成功！", null);
    }

    @Override
    public Result removeSinger(String account) {
        eventAdminMapper.deleteSinger(account);
        return Result.success(200, "删除成功！", null);
    }

    @Override
    public List<Singer> getAllSinger() {
        return eventAdminMapper.selectAllSinger();
    }
}
