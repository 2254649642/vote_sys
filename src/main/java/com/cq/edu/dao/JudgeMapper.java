package com.cq.edu.dao;

import com.cq.edu.pojo.Score;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface JudgeMapper {
    int insertJudgeScore(Score score);
    Score selectJudeScoreExist(Score score);
}
