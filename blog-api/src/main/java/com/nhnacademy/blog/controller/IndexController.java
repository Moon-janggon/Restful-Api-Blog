package com.nhnacademy.blog.controller;

import com.nhnacademy.blog.topic.domain.Topic;
import com.nhnacademy.blog.topic.dto.TopicResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class IndexController {
    @GetMapping
    public ResponseEntity<TopicResponse> getIndex() {
        TopicResponse topicResponse = new TopicResponse(
                1,null,"1",1
        );

        return ResponseEntity.ok(topicResponse);
    }
}
