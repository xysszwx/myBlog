package com.zwx.blog.web;

import com.github.pagehelper.PageInfo;
import com.zwx.blog.pojo.Blog;
import com.zwx.blog.pojo.Tag;
import com.zwx.blog.pojo.vo.BlogQuery;
import com.zwx.blog.pojo.vo.SelectPage;
import com.zwx.blog.service.BlogService;
import com.zwx.blog.service.TagService;
import com.zwx.blog.service.TagService;
import com.zwx.blog.service.TypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class TagShowController {

    @Resource
    private TagService tagService;

    @Resource
    private TypeService typeService;

    @Resource
    private BlogService blogService;

    @GetMapping("/tags/{id}")
    public String tags(@PathVariable Long id , Model model, SelectPage selectPage , @RequestParam(defaultValue = "1") int page){
        selectPage.setPageSize(6);
        //默认在第一页 可以传数据
        selectPage.setPageNum(page);
        List<Tag> tags = tagService.listTagTop(1000);
        for(Tag tag : tags){
            tag.setBlogs(blogService.queryBlogs(tag.getId()));
        }
        if(id == -1){
            id = tags.get(0).getId();
        }
        selectPage.setId(id);
        PageInfo<Blog> pageInfo = blogService.listByTag(selectPage);
        model.addAttribute("tags",tags);
        model.addAttribute("types1",typeService.listType());
        model.addAttribute("page",pageInfo);
        model.addAttribute("activeTagId",id);
        return "tags";
    }
}
