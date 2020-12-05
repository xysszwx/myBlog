package com.zwx.blog.web.admin;

import com.zwx.blog.pojo.User;
import com.zwx.blog.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("admin")
public class LoginController {

    @Resource
    private UserService userService;

    //登录页面
    @GetMapping
    public String loginPage(){
        return "admin/login";
    }

    //登录校验
    @PostMapping("/login")
    public String login(@RequestParam String username ,
                        @RequestParam String password,
                        HttpSession session,
                        RedirectAttributes attributes){

        List<User> list = userService.checkUser(username,password);

        if(null!=list && list.size()!=0){
            list.get(0).setPassword(null);
            session.setAttribute("user",list.get(0));
            return "admin/index";
        }
        else{
            //向前台传递校验信息
            attributes.addFlashAttribute("message","用户名或密码错误");
            return "redirect:/admin";
        }
    }

    //注销
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/admin";

    }




}
