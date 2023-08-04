package com.cq.edu.controller;

import com.cq.edu.pojo.Account;
import com.cq.edu.pojo.Result;
import com.cq.edu.pojo.Singer;
import com.cq.edu.service.AccountService;
import com.cq.edu.service.EventAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/eventadmin")
//@SessionAttributes({"usersInfo"})
public class EventAdminCotroller {

    @Autowired
    private EventAdminService eventAdminService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private Result result;


    /**
     * 歌手管理
     *
     * @param model
     * @return
     */
    @GetMapping("singerManager")
    public String browseAllarticles(Model model) {

        List<Singer> singerList = eventAdminService.selectAllChangePage(0);
        Collection<Singer> allSinger = eventAdminService.getAllSinger();

        Integer PageCount = allSinger.size();
        Integer endPage = 0;

        if (PageCount % 10 != 0) {
            endPage = PageCount / 10 + 1;
        } else  {
            endPage = PageCount / 10;
        }

//        Collections.reverse(userList);

        //放在请求域中
        model.addAttribute("singerList", singerList);
        model.addAttribute("endPage", endPage);
        model.addAttribute("curPage", 0);

        return "singerManager";
    }


    //点击下一页/上一页
    @GetMapping("singerManager/{page}")
    public String browseChangePage(@PathVariable("page") Integer page, Model model) {

        if (page < 0)
            return "redirect:/eventadmin/singerManager/" + (page + 1);

        List<Singer> singerList = eventAdminService.selectAllChangePage(page);
        Collection<Singer> allSinger = eventAdminService.getAllSinger();

        Integer PageCount = allSinger.size();
        Integer endPage = 0;

        if (PageCount % 10 != 0) {
            endPage = PageCount / 10 + 1;
        } else  {
            endPage = PageCount / 10;
        }
        if (page >= endPage) {
            return "redirect:/eventadmin/singerManager/" + (page - 1);
        }

        //放在请求域中
        model.addAttribute("singerList", singerList);
        model.addAttribute("endPage", endPage);
        model.addAttribute("curPage", page);

        return "singerManager";
    }


    @PostMapping("/updateSinger")
    @ResponseBody
    public Result updateUsersInfo(Singer singer) {
        return eventAdminService.updateSinger(singer);
    }

    /**
     * 新增歌手
     *
     * @param singer
     * @return
     */
    @PostMapping("/addUser")
    @ResponseBody
    public Result addUser(Singer singer) {
        return eventAdminService.addSinger(singer);
    }

    /**
     * 根据id删除账号
     *
     * @return
     */
    @DeleteMapping("/deleteSinger/{singerId}")
    @ResponseBody
    public Result removeAudience(@PathVariable String singerId) {
        return eventAdminService.removeSinger(singerId);
    }



}
