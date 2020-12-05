package com.zwx.blog.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zwx.blog.NotFoundException;
import com.zwx.blog.dao.TypeMapper;
import com.zwx.blog.pojo.Type;
import com.zwx.blog.pojo.TypeExample;
import com.zwx.blog.pojo.vo.SelectPage;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TypeServiceImpl implements TypeService{

    @Resource
    private TypeMapper typeMapper;


    @Transactional
    @Override
    public int saveType(Type type) {

        return  typeMapper.insert(type);
    }


    @Override
    public Type getTypeByid(Long id) {

        return typeMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Type> getTypeByName(String name) {
        TypeExample te = new TypeExample();
        TypeExample.Criteria cr = te.createCriteria();
        cr.andNameEqualTo(name);
        return typeMapper.selectByExample(te);
    }


    @Override
    public PageInfo<Type> listType(SelectPage selectPage) {

        PageHelper.startPage(selectPage.getPageNum(), selectPage.getPageSize());
        return new PageInfo(typeMapper.selectByParam(selectPage));
    }

    @Override
    public List<Type> listType() {
        return typeMapper.selectAll();
    }

    @Override
    public List<Type> listTypeTop(Integer size) {
        SelectPage selectPage = new SelectPage();
        selectPage.setPageSize(size);
        return typeMapper.selectTypeTop(selectPage);
    }




    @Transactional
    @Override
    public int updateType(Long id, Type type) {
        Type t= typeMapper.selectByPrimaryKey(id);
        if(t==null){
            throw new  NotFoundException();
        }

        return typeMapper.updateByPrimaryKey(type);
    }

    @Transactional
    @Override
    public void deleteType(Long id) {
        typeMapper.deleteByPrimaryKey(id);
    }
}
