package com.cq.edu.controller;

import com.cq.edu.pojo.*;
import com.cq.edu.service.AccountService;
import com.cq.edu.service.AdminService;
import com.cq.edu.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private Result result;

    @Autowired
    private MatchService matchService;


    /**
     * 用户管理
     *
     * @param model
     * @return
     */
    @GetMapping("userManager")
    public String browseUserManagers(Model model) {

        List<Account> userList = adminService.selectAllChangePage(0);
        Collection<Account> allAccount = accountService.getAllUser();

        Integer PageCount = allAccount.size();
        Integer endPage = 0;

        if (PageCount % 10 != 0) {
            endPage = PageCount / 10 + 1;
        } else  {
            endPage = PageCount / 10;
        }

//        Collections.reverse(userList);

        //放在请求域中
        model.addAttribute("userList", userList);
        model.addAttribute("endPage", endPage);
        model.addAttribute("curPage", 0);
        //浏览文章界面
        return "userManager";
    }

    //点击下一页/上一页
    @GetMapping("userManager/{page}")
    public String browseChangePage(@PathVariable("page") Integer page, Model model) {

        if (page < 0)
            return "redirect:/admin/userManager/" + (page + 1);

        List<Account> userList = adminService.selectAllChangePage(page);
        Collection<Account> allAccount = accountService.getAllUser();

        Integer PageCount = allAccount.size();
        Integer endPage = 0;

        if (PageCount % 10 != 0) {
            endPage = PageCount / 10 + 1;
        } else  {
            endPage = PageCount / 10;
        }
        if (page >= endPage) {
            return "redirect:/admin/userManager/" + (page - 1);
        }

        //放在请求域中
        model.addAttribute("userList", userList);
        model.addAttribute("endPage", endPage);
        model.addAttribute("curPage", page);

        return "userManager";
    }

    @PostMapping("/updateUsersInfo")
    @ResponseBody
    public Result updateUsersInfo(Account user) {
        return adminService.updateUsers(user);
    }


    /**
     * 新增用户
     *
     * @param user
     * @return
     */
    @PostMapping("/addUser")
    @ResponseBody
    public Result addUser(Account user) {
        return adminService.addUsers(user);
    }

    /**
     * 判断是否已经注册
     *
     * @param username
     * @return
     */
    @PostMapping("/isRegister")
    @ResponseBody
    public Result isRegistered(String username){
        return accountService.isRegistered(username);
    }

    /**
     * 根据account删除账号
     *
     * @return
     */
    @DeleteMapping("/deleteUser/{account}")
    @ResponseBody
    public Result removeAudience(@PathVariable String account) {
        return adminService.removeUsers(account);
    }


    /**
     * 比赛管理
     *
     * @return
     */
    @RequestMapping("/arrangeMatch")
    public String arrangeMatch(Model model) {
        List<Match> matchList = matchService.findAllMatch();

        model.addAttribute("matchedList", matchList);
        return "matchManager/arrangeMatch";
    }

    /**
     * 管理员自动编排比赛选手匹配
     *
     * @return
     */
    @RequestMapping("/autoGenerateMatch")
    @ResponseBody
    public Result addMatchInfo(String matchNumber) {
        matchService.autoGenerateMatch(matchNumber);
        result.setCode(200);
        return result;
    }

    /**
     * 管理员设置 开始投票
     *
     * @return
     */
    @RequestMapping("/startMatchVote")
    @ResponseBody
    public Result startMatchVote(String matchId) {
        return matchService.updateMatchState(matchId, "1");
    }


    /**
     * 管理员设置 停止投票
     *
     * @return
     */
    @RequestMapping("/stopMatchVote")
    @ResponseBody
    public Result stopMatchVote(String matchId, String playerA_id, String playerB_id) {
        result = matchService.stopMatchState(matchId, "2");
        if (result.getCode() == 200) {
            matchService.updateSingerState(playerA_id, "0");
            matchService.updateSingerState(playerB_id, "0");
        }
        return result;
    }


    /**
     * 显示投票详情
     *
     * @return
     */
    @RequestMapping("/votePage")
    public String votePage(Model model,@RequestParam(value = "matchId")String matchId,
                                        @RequestParam(value = "playerA_id")String playerA_id,
                                        @RequestParam(value = "playerB_id")String playerB_id) {
        model.addAttribute("matchId",matchId);
        model.addAttribute("playerA_id",playerA_id);
        model.addAttribute("playerB_id",playerB_id);
        return "votePage/votePage";
    }


    /**
     * 实时刷新票数信息
     *
     * @return
     */
    @RequestMapping("/showVote")
    @ResponseBody
    public Result<VoteDetails> showMatchVote(String matchId, String playerA_id, String playerB_id) {
        Result<VoteDetails> voteDetailsResult = matchService.getVoteDetailsByMatchId(matchId,playerA_id,playerB_id);
        return voteDetailsResult;
    }




    /**
     * 统计分数
     * @return
     */
    @RequestMapping("/analyzeScore")
    @ResponseBody
    public Result analyzeScore(String matchId, String playerA_id, String playerB_id) {
        result = matchService.addSumScore(matchId,playerA_id,playerB_id);
        return result;
    }

}
