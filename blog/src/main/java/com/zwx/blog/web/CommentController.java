package com.zwx.blog.web;

import com.zwx.blog.pojo.Comment;
import com.zwx.blog.pojo.User;
import com.zwx.blog.service.BlogService;
import com.zwx.blog.service.CommentService;
import com.zwx.blog.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Random;

@Controller
public class CommentController {

    @Resource
    private CommentService commentService;

    @Resource
    private UserService userService;

    @GetMapping("/comments/{blogId}")
    public String comments(@PathVariable  Long blogId, Model model){

        model.addAttribute("comments",commentService.listCommentByBlogId(blogId,model));
        return "blog :: commentList";
    }

    @PostMapping("/comments")
    public String post(Comment comment){
        User user = userService.selectByBlogId(comment.getBlogId());
        if(comment.getNickname() == "zwx"){
            comment.setAvatar(user.getAvatar());
            comment.setAdminComment(true);
        }else{
            Random r1 = new Random();
            int r2 = r1.nextInt(1000);
            comment.setAvatar("https://picsum.photos/seed/"+r2+"/100/100");

        }
        commentService.saveComment(comment);
        return "redirect:/comments/"+comment.getBlogId();
    }
}
