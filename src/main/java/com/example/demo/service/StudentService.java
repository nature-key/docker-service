package com.example.demo.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.dao.StudentPOMapper;
import com.example.demo.entity.StudentPO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service
public class StudentService {

    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);
    @Autowired
    StudentPOMapper studentPOMapper;

    @Resource
    RedisTemplate<String, Object> redisTemplate;

    public StudentPO getStudent(int id) {
        if (redisTemplate.opsForValue().get(id + "") != null) {
            StudentPO redisValueStudent = JSON.toJavaObject(JSONObject.
                    parseObject(JSON.toJSONString(redisTemplate.opsForValue().get(id + ""))), StudentPO.class);
            logger.info("redis value:{}", redisValueStudent);
            return redisValueStudent;
        }
        StudentPO studentPO = studentPOMapper.selectByPrimaryKey(id);
        redisTemplate.opsForValue().set(String.valueOf(id), studentPO);
        logger.info("mysql value:{}", studentPO);
        return studentPO;
    }
    public void addStudent(StudentPO studentPO) {
        logger.info("添加学生{}", studentPO);
        studentPOMapper.insert(studentPO);
        logger.info("添加成功");
    }
}
