package com.zwx.blog.service;

import com.zwx.blog.NotFoundException;
import com.zwx.blog.dao.CommentMapper;
import com.zwx.blog.pojo.Comment;
import com.zwx.blog.pojo.CommentExample;
import com.zwx.blog.pojo.vo.CommentModule;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{


    @Resource
    private CommentMapper commentMapper;

    @Override
    public List<Comment> listCommentByBlogId(Long id, Model model) {
        CommentExample commentExample = new CommentExample();
        CommentExample.Criteria criteria = commentExample.createCriteria();
        criteria.andBlogIdEqualTo(id);
        criteria.andParentCommentIdIsNull();
        commentExample.setOrderByClause("create_time desc");
        List<Comment> comments = commentMapper.selectByExample(commentExample);
        return  eachComment(comments);
    }

    @Transactional
    @Override
    public void saveComment(Comment comment) {
        comment.setCreateTime(new Date());
        if(comment.getParentCommentId() == -1){
            comment.setParentCommentId(null);
        }
        if(commentMapper.insert(comment)<1){
            throw new NotFoundException("添加评论失败");
        }
    }





    /**
     * 循环每个顶级的评论节点
     * @param comments
     * @return
     */
    private List<Comment>  eachComment(List<Comment> comments) {
        List<Comment> commentsView = new ArrayList<>();
        for (Comment comment : comments) {
            Comment c = new Comment();
            BeanUtils.copyProperties(comment,c);
            commentsView.add(c);
        }
        //合并评论的各层子代到第一级子代集合中

        combineChildren(commentsView);
        return commentsView;
    }

    /**
     *
     * @param comments root根节点，blog不为空的对象集合
     * @return
     */
    private void combineChildren(List<Comment> comments) {
        List<CommentModule> commentModules = new ArrayList<>();



        for (Comment comment : comments) {
            Long id = comment.getId();
            List<Comment> replys1 = replyComment(id);
            for(Comment reply1 : replys1) {
                //循环迭代，找出子代，存放在tempReplys中
                recursively(reply1);
            }
            //修改顶级节点的reply集合为迭代处理后的集合


            comment.setReplyComment(tempReplys);

            //清除临时存放区
            tempReplys = new ArrayList<>();
        }


    }

    //存放迭代找出的所有子代的集合
    private List<Comment> tempReplys = new ArrayList<>();
    /**
     * 递归迭代，剥洋葱
     * @param comment 被迭代的对象
     * @return
     */
    private void recursively(Comment comment) {
        tempReplys.add(comment);//顶节点添加到临时存放集合
        List<Comment> replys1 = replyComment(comment.getId());

        if (replys1.size()>0) {
            List<Comment> replys = replys1;
            for (Comment reply : replys) {
                tempReplys.add(reply);
                List<Comment> replys2 = replyComment(reply.getId());
                if (replys2.size()>0) {
                    recursively(reply);
                }
            }
        }
    }


    private List<Comment> replyComment(Long id){
        CommentExample commentExample = new CommentExample();
        CommentExample.Criteria criteria = commentExample.createCriteria();
        criteria.andParentCommentIdEqualTo(id);
        List<Comment> reply = commentMapper.selectByExample(commentExample);
        return reply;
    }
}
