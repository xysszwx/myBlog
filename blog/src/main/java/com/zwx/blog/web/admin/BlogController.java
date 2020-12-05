package com.zwx.blog.web.admin;

import com.github.pagehelper.PageInfo;
import com.zwx.blog.pojo.Blog;
import com.zwx.blog.pojo.User;
import com.zwx.blog.pojo.vo.BlogQuery;
import com.zwx.blog.service.BlogService;
import com.zwx.blog.service.TagService;
import com.zwx.blog.service.TypeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class BlogController {

    private static final String INPUT = "admin/blogs-input";
    private static final String LIST = "admin/blogs";
    private static final String REDIRECT_LIST = "redirect:/admin/blogs";


    @Resource
    private BlogService blogService;
    @Resource
    private TypeService typeService;
    @Resource
    private TagService tagService;

    //到达博客管理页面
    @GetMapping("blogs")
    public String blogs(BlogQuery blogQuery , @RequestParam(defaultValue = "1") int page, Model model){
        blogQuery.setPageNum(page);
        blogQuery.setPageSize(5);
        PageInfo<Blog> pageInfo = blogService.listBlog(blogQuery);
        model.addAttribute("types",typeService.listType());
        model.addAttribute("page",pageInfo);

        return LIST;
    }

    @PostMapping("blogs/search")
    public String search(BlogQuery blogQuery , @RequestParam(defaultValue = "1") int page, Model model){
        blogQuery.setPageNum(page);
        blogQuery.setPageSize(5);
        PageInfo<Blog> pageInfo = blogService.listBlog(blogQuery);
        model.addAttribute("page",pageInfo);
        System.out.println(pageInfo.getList());
        return "admin/blogs :: blogList";
    }

    @GetMapping("/blogs/input")
    public String bloginput(Model model){
        setTypeAndTag(model);
        model.addAttribute("blog",new Blog());
        return INPUT;
    }

    private void setTypeAndTag(Model model){
        model.addAttribute("types",typeService.listType());
        model.addAttribute("tags",tagService.listTag());
    }

    @GetMapping("/blogs/{id}/input")
    public String eidtbloginput(@PathVariable Long id, Model model){
        setTypeAndTag(model);
        Blog blog = blogService.getBlog(id);
        List<Long> longs = tagService.listTagId(id);
        String ids = StringUtils.join(longs,",");
        blog.setTagIds(ids);
        model.addAttribute("blog",blog);
        return INPUT;
    }

    @PostMapping("/blogs")
    public String save(Blog blog, RedirectAttributes attributes, HttpSession session){
       User user= (User)session.getAttribute("user");
        blog.setUserId(user.getId());
        int i= 0;
        if(blog.getId()!=null){
            i=blogService.updateBlog(blog.getId(),blog);

        }else{
            i = blogService.saveBlog(blog);

        }


        if(i<1){
            attributes.addFlashAttribute("message","操作失败");
        }
        attributes.addFlashAttribute("message","操作成功");
        return REDIRECT_LIST;
    }

    @GetMapping("/blogs/{id}/delete")
    public String deleteBlog(@PathVariable Long id ,RedirectAttributes attributes){
        blogService.deleteBlog(id);
        attributes.addFlashAttribute("message" , "删除成功");
        return REDIRECT_LIST;
    }

}
