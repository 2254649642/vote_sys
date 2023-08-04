package com.cq.edu.controller;

import com.cq.edu.pojo.Result;
import com.cq.edu.pojo.VoteCount;
import com.cq.edu.service.AccountService;
import com.cq.edu.service.AdminService;
import com.cq.edu.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/viewer")
public class ViewerController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private Result result;

    @Autowired
    private MatchService matchService;

    /**
     * 观众投票操作
     * @return result
     */
    @RequestMapping("/addVoteNum")
    @ResponseBody
    public Result addVoteNum(VoteCount voteCount) {
        return matchService.addVoteCountRecordByTick(voteCount);
    }
}
