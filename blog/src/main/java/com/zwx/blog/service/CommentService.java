package com.zwx.blog.service;

import com.zwx.blog.pojo.Comment;
import org.springframework.ui.Model;

import java.util.List;

public interface CommentService {

    List<Comment> listCommentByBlogId(Long id, Model model);

    void saveComment(Comment comment);
}
