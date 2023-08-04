package com.cq.edu.service;

import com.cq.edu.pojo.Account;
import com.cq.edu.pojo.Result;
import com.cq.edu.pojo.Singer;

import java.util.Collection;
import java.util.List;

public interface EventAdminService {

    List<Singer> selectAllChangePage(Integer page);

    Result updateSinger(Singer singer);

    Result addSinger(Singer singer);

    Result removeSinger(String id);

    Collection<Singer> getAllSinger();
}
