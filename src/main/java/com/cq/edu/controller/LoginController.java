package com.cq.edu.controller;

import com.cq.edu.dao.AccountMapper;
import com.cq.edu.pojo.Account;
import com.cq.edu.pojo.Authority;
import com.cq.edu.pojo.Result;
import com.cq.edu.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.List;


@Controller
@RequestMapping("/user")
@SessionAttributes({"usersInfo"})
public class LoginController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private Result result;

    @GetMapping("/login")
    public String login() {
        return "login";
    }


    /**
     * 登录成功后的地址
     * @return
     */
    @RequestMapping("/index")
    @ResponseBody
    public Result index(Model model, HttpSession session) {
        // 从当前HttpSession获取绑定到此会话的所有对象的名称
        Enumeration<String> names = session.getAttributeNames();
        UserDetails principal = null;
        while (names.hasMoreElements()){
            // 获取HttpSession中会话名称
            String element = names.nextElement();
            // 获取HttpSession中的应用上下文
            SecurityContextImpl attribute = (SecurityContextImpl) session.getAttribute(element);
            System.out.println("element: "+element);
            System.out.println("attribute: "+attribute);
            // 获取用户相关信息
            Authentication authentication = attribute.getAuthentication();
            principal = (UserDetails)authentication.getPrincipal();
            System.out.println(principal);
            System.out.println("username: "+principal.getUsername());
        }
        Account account = accountService.getAccount(principal.getUsername());
        model.addAttribute("usersInfo", account);
        return Result.success(200, "登录成功！", account);
    }

    /**
     * 登录失败后的情况
     */
    @RequestMapping("/errors")
    public Result error(Model model) {
        result.setCode(500);
        result.setMessage("登录失败,账号或者密码错误!");
        return Result.success(200, "登录成功！", null);
    }
    /**
     * 主页面路径映射
     *
     * @return
     */
    @RequestMapping("/main")
    public ModelAndView main() {
        ModelAndView modelAndView = new ModelAndView();
        List<Account> usersList = accountService.getAllUser();
        modelAndView.addObject("userNumbers", usersList.size());
        modelAndView.setViewName("main");
        return modelAndView;
    }

    /**
     * 注册地址映射
     *
     * @return
     */
    @GetMapping("/register")
    public String register() {
        return "register";
    }


    /**
     * 注册成功
     *
     * @param user
     * @return
     */
    @PostMapping("/doRegister")
    @ResponseBody
    public Result doRegister(Account user) {
        return accountService.addUsers(user);
    }



}
