package com.example.lock.controller;

import com.example.lock.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PessimisticController {

    @Autowired
    private TestService testService;

    @PostMapping("pessimisticTest")
    public Object pessimisticTest(@RequestParam String type) throws Exception {
        return testService.pessimisticTest(type);
    }
}
