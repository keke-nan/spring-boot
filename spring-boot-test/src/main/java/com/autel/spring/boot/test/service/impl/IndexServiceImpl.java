package com.autel.spring.boot.test.service.impl;

import com.autel.spring.boot.test.service.IndexService;
import org.springframework.stereotype.Service;

/**
 * @author A20019
 * @date 2020/3/26
 */

@Service
public class IndexServiceImpl implements IndexService {

    @Override
    public String app(String info) {
        System.out.println("is serviceImpl");
        return "is success，" + info;
    }
}
