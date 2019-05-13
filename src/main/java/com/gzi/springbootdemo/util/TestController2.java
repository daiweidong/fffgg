package com.gzi.springbootdemo.util;


import com.gzi.springbootdemo.dataobject.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController2 {
    @GetMapping("/send")
    @ResponseBody
    public String sendDirectQueue() {
        User user=new User();
        user.setName("lllll");
        user.setUserId("9999999");
       Send.send(user);
        return "ok";
    }
    @GetMapping("/send2")
    @ResponseBody
    public String sendDirectQueue2() {
        User user=new User();
        user.setName("测试");
        user.setUserId("11111");
        Send.send(user);
        return "ok";
    }
    @GetMapping("/Receive")
    @ResponseBody
    public String ReceiveDirectQueue() {
        Receive.Receive();
        return "ok";
    }
}
