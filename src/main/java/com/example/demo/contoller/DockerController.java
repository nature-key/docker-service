package com.example.demo.contoller;


import com.example.demo.entity.StudentPO;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class DockerController {
    @Autowired
    StudentService studentService;

    @RequestMapping("/docker/{id}")
    public StudentPO getStudent(@PathVariable("id") Integer id) {
        return studentService.getStudent(id);
    }

    @PostMapping("/docker/add")
    public void addStudent(@RequestBody StudentPO studentPO) {
        studentService.addStudent(studentPO);
    }
}
