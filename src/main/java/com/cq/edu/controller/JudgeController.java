package com.cq.edu.controller;

import com.cq.edu.dao.JudgeMapper;
import com.cq.edu.pojo.Result;
import com.cq.edu.pojo.Score;
import com.cq.edu.service.AccountService;
import com.cq.edu.service.AdminService;
import com.cq.edu.service.JudgeService;
import com.cq.edu.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/judge")
public class JudgeController {
    @Autowired
    private JudgeService judgeService;

    @Autowired
    private Result result;



    /**
     * 评委进行评分
     *
     * @param score
     * @return
     */
    @RequestMapping("/addJudgeScore")
    @ResponseBody
    public Result addScore(Score score) {
        return judgeService.addJudgeScore(score);
    }
}
