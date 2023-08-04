package com.cq.edu.dao;

import com.cq.edu.pojo.Account;
import com.cq.edu.pojo.Authority;
import com.cq.edu.pojo.Result;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AccountMapper {

    int insertAccount(Account account);

    Account selectAccount(String s);

    List<Authority> selectAuths(String s);

    List<Account> selectAllUser();

    int updatePasswd(Account user);

    int updateUserInfor(Account user);

}
