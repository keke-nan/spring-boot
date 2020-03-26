package com.autel.spring.boot.test.controller;

import com.autel.spring.boot.test.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author A20019
 * @date 2020/3/26
 */

@RestController
@RequestMapping("/index")
public class IndexController {

    @Autowired
    private IndexService service;

    @GetMapping("/dex")
    public String dex(){
        return "this is no param";
    }

    @PostMapping("/save")
    public String save(String conent){
        System.out.println("save conent: " + conent);
        return "save success,and conent: " + conent;
    }

    @GetMapping("/app")
    public String getApp(String info){
        System.out.println("is controller,"+ info);
        return service.app(info);
    }
}
