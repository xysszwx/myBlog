package com.zwx.blog.dao;

import com.zwx.blog.pojo.Type;
import com.zwx.blog.pojo.TypeExample;
import java.util.List;

import com.zwx.blog.pojo.vo.SelectPage;
import org.apache.ibatis.annotations.Param;

public interface TypeMapper {
    int countByExample(TypeExample example);

    int deleteByExample(TypeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Type record);

    int insertSelective(Type record);

    List<Type> selectByExample(TypeExample example);

    Type selectByPrimaryKey(Long id);

    //查询全部 自定义
    List<Type> selectByParam(SelectPage selectPage);

    List<Type> selectAll();

    int updateByExampleSelective(@Param("record") Type record, @Param("example") TypeExample example);

    int updateByExample(@Param("record") Type record, @Param("example") TypeExample example);

    //不修改为空的属性
    int updateByPrimaryKeySelective(Type record);

    //按照主键更新 属性为空也修改
    int updateByPrimaryKey(Type record);

    List<Type> selectTypeTop(SelectPage SelectPage);
}