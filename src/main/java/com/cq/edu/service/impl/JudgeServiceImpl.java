package com.cq.edu.service.impl;

import com.cq.edu.dao.JudgeMapper;
import com.cq.edu.dao.MatchMapper;
import com.cq.edu.pojo.Match;
import com.cq.edu.pojo.Result;
import com.cq.edu.pojo.Score;
import com.cq.edu.service.EventAdminService;
import com.cq.edu.service.JudgeService;
import com.cq.edu.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JudgeServiceImpl implements JudgeService {
    @Autowired
    private JudgeMapper judgeMapper;

    @Autowired
    private MatchService matchService;

    @Autowired
    private Result result;


    @Override
    public Result addJudgeScore(Score score) {
        Score scoreTmp = judgeMapper.selectJudeScoreExist(score);
        Match match = matchService.getMatchStateById(score.getMatch_id()); //1">正在投票中 2">投票已结束 3">投票未开始
        if (match.getState().equals("2")) {
            result.setCode(600);
            return result;
        }

        if (scoreTmp != null) {
            result.setCode(500);
            result.setMessage("该选手已经评分完成");
            return result;
        }
        judgeMapper.insertJudgeScore(score);
        result.setCode(200);
        result.setMessage("成功完成评分");
        return result;
    }
}
