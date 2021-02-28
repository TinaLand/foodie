package com.imooc.controller;


import com.imooc.service.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // json object
public class StuFooController {

    @Autowired
    private StuService stuService;

    @Transactional(propagation = Propagation.SUPPORTS)
    @GetMapping("/getStu")
    public Object getStu(int id) {
        return stuService.getStuInfo(id);
    }
}
