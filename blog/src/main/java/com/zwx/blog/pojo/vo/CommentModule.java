package com.zwx.blog.pojo.vo;

import com.zwx.blog.pojo.Comment;

import java.util.List;

public class CommentModule {
   private  List<Comment> replyComment;

    public List<Comment> getReplyComment() {
        return replyComment;
    }

    public void setReplyComment(List<Comment> replyComment) {
        this.replyComment = replyComment;
    }
}
