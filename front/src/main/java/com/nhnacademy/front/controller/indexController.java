package com.nhnacademy.front.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
public class indexController {

    @GetMapping(value = "/")
    public String index(
            Model model,
            @RequestParam(value = "topic_id", required = false) Integer topicId,
            @RequestParam(value = "topic_pid", required = false) Integer topicPid
    ) {

        model.addAttribute("topicId", topicId);
        model.addAttribute("topicPid", topicPid);
        return "index/index";
    }

}
