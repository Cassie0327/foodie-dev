package com.imooc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CAIQIAN04
 */
@RestController
public class HelloController {
    @GetMapping("/hello")
    public Object hello()
    {
        return "hello world";

    }
}
