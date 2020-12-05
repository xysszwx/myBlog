package com.zwx.blog.web.admin;

import com.zwx.blog.pojo.Type;
import com.zwx.blog.pojo.vo.SelectPage;
import com.zwx.blog.service.TypeService;
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
public class TypeController {

    @Resource
    private TypeService typeService;

    //分页查询分类
    @GetMapping("/types")
    public String typelist(SelectPage selectPage, @RequestParam(defaultValue = "1") int page, Model model){
            //默认4条一页
            selectPage.setPageSize(4);
            //默认在第一页 可以传数据
            selectPage.setPageNum(page);
        model.addAttribute("page",typeService.listType(selectPage));
        return "admin/types";
    }

    //分类添加页面
    @GetMapping("/types/input")
    public String input(Model model) {
        model.addAttribute("type", new Type());
        return "admin/type-input";
    }

    //添加操作
    //在实体类上添加了@NoBlank和在参数前添加了@Valid 判空  参数列表中 一定要与BindingResult相邻
    @PostMapping("/types")
    public String save(@Valid Type type , BindingResult result, RedirectAttributes attributes){


        List<Type> typeByName = typeService.getTypeByName(type.getName());

        if(typeByName!=null && typeByName.size()!=0){
            result.rejectValue("name","nameError","不能添加重复的分类");
        }

        if(result.hasErrors()){
            return "admin/type-input";
        }

        int i = typeService.saveType(type);
        if(i<1){
            attributes.addFlashAttribute("message" , "新增失败");
        }else{
            attributes.addFlashAttribute("message" , "新增成功");
        }
        return "redirect:/admin/types";
    }

    @GetMapping("/types/{id}/input")
    public String editType(@PathVariable Long id ,Model model){
        model.addAttribute("type" ,typeService.getTypeByid(id));
        return "admin/type-input";
    }


    @PostMapping("/types/{id}")
    public String editsave(@Valid Type type , BindingResult result,
                           @PathVariable Long id, RedirectAttributes attributes){



        List<Type> typeByName = typeService.getTypeByName(type.getName());
        System.out.println(typeByName);
        if(typeByName!=null && typeByName.size()!= 0){
            result.rejectValue("name","nameError","不能添加重复的分类");
        }

        if(result.hasErrors()){
            return "admin/type-input";
        }

        int i = typeService.updateType(id,type);
        if(i<1){
            attributes.addFlashAttribute("message" , "更新失败");
        }else{
            attributes.addFlashAttribute("message" , "更新成功");
        }
        return "redirect:/admin/types";
    }

    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable Long id ,RedirectAttributes attributes){

            typeService.deleteType(id);
            attributes.addFlashAttribute("message" , "删除成功");
            return "redirect:/admin/types";
    }

}
