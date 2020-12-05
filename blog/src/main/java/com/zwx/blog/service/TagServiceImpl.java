package com.zwx.blog.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zwx.blog.NotFoundException;
import com.zwx.blog.dao.TagMapper;
import com.zwx.blog.pojo.Tag;
import com.zwx.blog.pojo.TagExample;
import com.zwx.blog.pojo.vo.SelectPage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class TagServiceImpl implements TagService{

    @Resource
    private TagMapper tagMapper;


    @Transactional
    @Override
    public int saveTag(Tag tag) {

        return  tagMapper.insert(tag);
    }


    @Override
    public Tag getTagByid(Long id) {

        return tagMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Tag> getTagByName(String name) {
        TagExample te = new TagExample();
        TagExample.Criteria cr = te.createCriteria();
        cr.andNameEqualTo(name);
        return tagMapper.selectByExample(te);
    }

    @Override
    public List<Tag> listTag() {
        return tagMapper.selectAll();
    }

    @Override
    public List<Tag> listByIds(String ids) {
        TagExample tagExample = new TagExample();
        TagExample.Criteria cr = tagExample.createCriteria();
        cr.andIdIn(convertToList(ids));
        return tagMapper.selectByExample(tagExample);
    }

    @Override
    public List<Long> listTagId(Long id) {

        return tagMapper.selectId(id);
    }

    @Override
    public List<Tag> listTagTop(Integer size) {
        SelectPage selectPage = new SelectPage();
        selectPage.setPageSize(size);
        return tagMapper.selectTagTop(selectPage);
    }


    @Override
    public PageInfo<Tag> listTag(SelectPage selectPage) {

        PageHelper.startPage(selectPage.getPageNum(), selectPage.getPageSize());
        return new PageInfo(tagMapper.selectByParam(selectPage));
    }





    public  static List<Long> convertToList(String ids) {
        List<Long> list = new ArrayList<>();
        if (!"".equals(ids) && ids != null) {
            String[] idarray = ids.split(",");
            for (int i=0; i < idarray.length;i++) {
                list.add(new Long(idarray[i]));
            }
        }
        return list;
    }

    @Transactional
    @Override
    public int updateTag(Long id, Tag tag) {
        Tag t= tagMapper.selectByPrimaryKey(id);
        if(t==null){
            throw new  NotFoundException();
        }

        return tagMapper.updateByPrimaryKey(tag);
    }

    @Transactional
    @Override
    public void deleteTag(Long id) {
        tagMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Tag> queryByBlogId(Long id) {
        return tagMapper.selectByBlogId(id);
    }
}
