package com.cq.edu.controller;

import com.cq.edu.pojo.Account;
import com.cq.edu.pojo.Result;
import com.cq.edu.pojo.SumScore;
import com.cq.edu.service.AccountService;
import com.cq.edu.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/common")
//@SessionAttributes({"usersInfo"})
public class CommonCotroller {

    @Autowired
    private AccountService accountService;

    @Autowired
    private MatchService matchService;

    @RequestMapping("/personInfo")
    public ModelAndView personInfo(HttpSession httpSession) {
        ModelAndView modelAndView = new ModelAndView();
        Account tmpUser = (Account)httpSession.getAttribute("usersInfo");
        Account account = accountService.getAccount(tmpUser.getAccount());
        modelAndView.setViewName("personInfo");
        modelAndView.addObject("user", account);
        return modelAndView;
    }

    /**
     * 修改密码
     *
     * @return
     */
    @GetMapping("/editPassword")
    public String editPassword() {
        return "edit_password";
    }

    /**
     * 更改密码
     *
     * @param authentication
     * @param user
     * @return
     */
    @PostMapping("/doEditPassword")
    @ResponseBody
    public Result doEditPassword(Authentication authentication, Account user) {
        user.setAccount(authentication.getName());
        return accountService.changePasswd(user);
    }

    /**
     * 更新个人信息
     * @param user
     * @return
     */
    @PostMapping("/updateInfor")
    @ResponseBody
    public Result updateInfor(Account user, HttpSession httpSession) {
        Account tmpUser = (Account)httpSession.getAttribute("usersInfo");
        user.setAccount(tmpUser.getAccount());
        return accountService.changeUserInfor(user);
    }


    /**
     * 排行榜页面映射
     *
     * @return
     */
    @RequestMapping("/rankings")
    public String rankings(Model model) {
        List<SumScore> scoreList = matchService.getAllSumScoreBySort();
        model.addAttribute("scoreList", scoreList);
        return "/matchManager/rankings";
    }

}
