package com.cq.edu.dao;

import com.cq.edu.pojo.Account;
import com.cq.edu.pojo.Singer;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper
public interface EventAdminMapper {

    List<Singer> selectAllChangePage(Integer page);
    int updateSingerInfo(Singer singer);
    int insertSinger(Singer singer);
    int deleteSinger(String id);
    List<Singer> selectAllSinger();

}

