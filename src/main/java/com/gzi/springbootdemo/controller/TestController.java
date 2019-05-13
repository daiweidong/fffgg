package com.gzi.springbootdemo.controller;


import com.gzi.springbootdemo.Service.SenderService;
import com.gzi.springbootdemo.config.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TestController {

    @Autowired
    private Sender sender;
    @Autowired
    private SenderService senderService;

  /*  @Autowired
    private DelaySender delaySender;*/

    @GetMapping("/sendDirectQueue")
    public Object sendDirectQueue() {
        //sender.sendDirectQueue();
        senderService.sendDirectQueue("lllllll");

        return "ok";
    }

    @GetMapping("/sendTopic")
    public Object sendTopic() {
       // sender.sendTopic();
        senderService.sendTopic("ffffffff");
        return "ok";
    }

    @GetMapping("/sendFanout")
    public Object sendFanout() {
      //  sender.sendFanout();
        senderService.sendFanout("ddddd");
        return "ok";
    }


}
