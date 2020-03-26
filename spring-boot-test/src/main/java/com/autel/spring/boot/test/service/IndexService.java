package com.autel.spring.boot.test.service;

import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author A20019
 * @date 2020/3/26
 */
public interface IndexService {

    @GetMapping("/app")
    String app(String info);
}
