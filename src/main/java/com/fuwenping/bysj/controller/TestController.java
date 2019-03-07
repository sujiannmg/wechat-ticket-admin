package com.fuwenping.bysj.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

  private Logger logger = LoggerFactory.getLogger(TestController.class);

  @GetMapping("/")
  public String hello2(Model model){
    logger.info("test-----------------------------");
    return "/starter";
  }

}

