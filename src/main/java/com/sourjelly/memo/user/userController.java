package com.sourjelly.memo.user;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user")
@Controller
public class userController {

    @GetMapping("/join")
    public String join(){
        return "user/join";
    }
}
