package com.nhnacademy.front.index.dto;

import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.util.ArrayList;
import java.util.List;

@Value
@RequiredArgsConstructor
public class TopicResponse {
    Integer topicId;
    Integer topicPid;
    String topicName;
    Integer topicSec;
    List<TopicResponse> childrenTopics = new ArrayList<>();
}