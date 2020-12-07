package com.zwx.blog.web;

import com.zwx.blog.pojo.Comment;
import com.zwx.blog.service.BlogService;
import com.zwx.blog.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;

@Controller
public class CommentController {

    @Resource
    private CommentService commentService;



    @GetMapping("/comments/{blogId}")
    public String comments(@PathVariable  Long blogId, Model model){

        model.addAttribute("comments",commentService.listCommentByBlogId(blogId,model));
        return "blog :: commentList";
    }

    @PostMapping("/comments")
    public String post(Comment comment){
        comment.setAvatar("https://picsum.photos/seed/100/100/100");
        commentService.saveComment(comment);
        return "redirect:/comments/"+comment.getBlogId();
    }
}
