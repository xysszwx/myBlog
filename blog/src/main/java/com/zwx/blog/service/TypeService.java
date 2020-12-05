package com.zwx.blog.service;

import com.github.pagehelper.PageInfo;
import com.zwx.blog.pojo.Type;
import com.zwx.blog.pojo.vo.SelectPage;

import java.util.List;


public interface TypeService {
    int saveType(Type type);

    Type getTypeByid(Long id);

    List<Type> getTypeByName(String name);

    PageInfo listType(SelectPage selectPage);

    List<Type> listType();

    List<Type> listTypeTop(Integer size);

    int  updateType(Long id,Type type);

    void deleteType(Long id);
}
