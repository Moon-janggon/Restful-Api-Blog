package com.nhnacademy.front.index.adaptor;


import com.nhnacademy.front.index.dto.TopicResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "indexAdaptor", url = "http://localhost:8080", path = "/")
public interface IndexAdaptor
{
    @GetMapping
    public ResponseEntity<TopicResponse> getIndex();

}
