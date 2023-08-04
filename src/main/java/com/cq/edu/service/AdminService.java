package com.cq.edu.service;

import com.cq.edu.pojo.Account;
import com.cq.edu.pojo.Result;

import java.util.List;

public interface AdminService {
    List<Account> selectAllChangePage(Integer page);

    Result updateUsers(Account user);

    Result addUsers(Account user);

    Result removeUsers(String id);
}
