package com.imooc.controller;


import com.imooc.service.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@RestController // json object
@ApiIgnore
public class StuFooController {

    @Autowired
    private StuService stuService;

    @Transactional(propagation = Propagation.SUPPORTS)
    @GetMapping("/getStu")
    public Object getStu(int id) {
        return stuService.getStuInfo(id);
    }

    @PostMapping ("/saveStu")
    public Object  saveStu() {
        stuService.saveStu();
        return "ok";
    }

    @PostMapping ("/updateStu")
    public Object  updateStu(int id) {
        stuService.updateStu(id);
        return "ok";
    }

    @PostMapping ("/deleteStu")
    public Object  deleteStu(int id) {
        stuService.deleteStu(id);
        return "ok";
    }
}
