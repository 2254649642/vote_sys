package com.cq.edu.dao;

import com.cq.edu.pojo.Account;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminMapper {

    List<Account> selectAllChangePage(Integer page);
    int updateUsersInfo(Account account);
    int insertUser(Account account);
    int deleteUser(String account);
}
