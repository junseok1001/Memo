package com.sourjelly.memo.hello;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloWorldController {

    @GetMapping("/helloWorld")
    @ResponseBody
    public String hello(){
        return "Hello World";
    }


}
