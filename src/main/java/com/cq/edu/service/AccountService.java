package com.cq.edu.service;

import com.cq.edu.pojo.Account;
import com.cq.edu.pojo.Authority;
import com.cq.edu.pojo.Result;

import java.util.List;

public interface AccountService {
    int insertAccount(Account account);
    List<Authority> selectAuths(String s);
    List<Account> getAllUser();
    Account getAccount(String username);
    Result isRegistered(String email);
    Result addUsers(Account user);
    Result changePasswd(Account user);
    Result changeUserInfor(Account user);
}
