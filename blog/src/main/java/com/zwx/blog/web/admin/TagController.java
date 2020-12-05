package com.zwx.blog.web.admin;

import com.zwx.blog.pojo.Tag;
import com.zwx.blog.pojo.vo.SelectPage;
import com.zwx.blog.service.TagService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class TagController {

    @Resource
    private TagService tagService;

    //分页查询分类
    @GetMapping("/tags")
    public String taglist(SelectPage selectPage, @RequestParam(defaultValue = "1") int page, Model model){
            //默认4条一页
            selectPage.setPageSize(4);
            //默认在第一页 可以传数据
            selectPage.setPageNum(page);
        model.addAttribute("page",tagService.listTag(selectPage));
        return "admin/tags";
    }

    //分类添加页面
    @GetMapping("/tags/input")
    public String input(Model model) {
        model.addAttribute("tag", new Tag());
        return "admin/tag-input";
    }

    //添加操作
    //在实体类上添加了@NoBlank和在参数前添加了@Valid 判空  参数列表中 一定要与BindingResult相邻
    @PostMapping("/tags")
    public String save(@Valid Tag tag , BindingResult result, RedirectAttributes attributes){


        List<Tag> tagByName = tagService.getTagByName(tag.getName());

        if(tagByName!=null && tagByName.size()!=0){
            result.rejectValue("name","nameError","不能添加重复的分类");
        }

        if(result.hasErrors()){
            return "admin/tag-input";
        }

        int i = tagService.saveTag(tag);
        if(i<1){
            attributes.addFlashAttribute("message" , "新增失败");
        }else{
            attributes.addFlashAttribute("message" , "新增成功");
        }
        return "redirect:/admin/tags";
    }

    @GetMapping("/tags/{id}/input")
    public String editTag(@PathVariable Long id ,Model model){
        model.addAttribute("tag" ,tagService.getTagByid(id));
        return "admin/tag-input";
    }


    @PostMapping("/tags/{id}")
    public String editsave(@Valid Tag tag , BindingResult result,
                           @PathVariable Long id, RedirectAttributes attributes){



        List<Tag> tagByName = tagService.getTagByName(tag.getName());
        System.out.println(tagByName);
        if(tagByName!=null && tagByName.size()!= 0){
            result.rejectValue("name","nameError","不能添加重复的分类");
        }

        if(result.hasErrors()){
            return "admin/tag-input";
        }

        int i = tagService.updateTag(id,tag);
        if(i<1){
            attributes.addFlashAttribute("message" , "更新失败");
        }else{
            attributes.addFlashAttribute("message" , "更新成功");
        }
        return "redirect:/admin/tags";
    }

    @GetMapping("/tags/{id}/delete")
    public String delete(@PathVariable Long id ,RedirectAttributes attributes){

            tagService.deleteTag(id);
            attributes.addFlashAttribute("message" , "删除成功");
            return "redirect:/admin/tags";
    }

}
