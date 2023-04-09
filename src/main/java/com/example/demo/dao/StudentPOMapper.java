package com.example.demo.dao;

import com.example.demo.entity.StudentPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentPOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StudentPO record);

    StudentPO selectByPrimaryKey(Integer id);

    List<StudentPO> selectAll();

    int updateByPrimaryKey(StudentPO record);
}