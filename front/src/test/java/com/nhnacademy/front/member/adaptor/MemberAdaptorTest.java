package com.nhnacademy.front.member.adaptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.nhnacademy.front.member.dto.MemberResponse;
import feign.FeignException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;


@SpringBootTest
class MemberAdaptorTest {
    Logger log =LoggerFactory.getLogger(MemberAdaptorTest.class);

    @Autowired
    MemberAdaptor memberAdaptor;

    @Test
    void getMem() throws IOException {

        URL url = new URL("http://localhost:8080/api/blog/members/1");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream());
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = bufferedReader.readLine()) != null) {
            response.append(inputLine);
            response.append("\n");
        }
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        MemberResponse memberResponse = objectMapper.readValue(response.toString(), MemberResponse.class);


        bufferedReader.close();
        log.info("response {} ", memberResponse);
    }

    @Test
    @DisplayName("member 조회")
    void getMember() {
        ResponseEntity<MemberResponse> memberResponseResponseEntity = memberAdaptor.getMember(1l);
        MemberResponse memberResponse = memberResponseResponseEntity.getBody();
        Assertions.assertNotNull(memberResponse);
        Assertions.assertAll(
                ()->Assertions.assertEquals(1l, memberResponse.getMbNo()),
                ()->Assertions.assertEquals("marco1@nhnacademy.com", memberResponse.getMbEmail()),
                ()->Assertions.assertEquals("마르코1",memberResponse.getMbName()),
                ()->Assertions.assertEquals("011222200001", memberResponse.getMbMobile()),
                ()->Assertions.assertNotNull(memberResponse.getCreatedAt())
        );
        log.debug("memberResponse:{}", memberResponse);
    }

    @Test
    @DisplayName("1page 회원조회")
    void getMembers() {
        ResponseEntity<List<MemberResponse>> memberListResponseEntity = memberAdaptor.getMembers(1);
        List<MemberResponse> memberResponseList = memberListResponseEntity.getBody();
        log.debug("memberListResponse:{}", memberResponseList);
        Assertions.assertEquals(5, memberResponseList.size());
    }

    @Test
    void deleteMember() {

        memberAdaptor.deleteMember(5l);
        Assertions.assertThrows(FeignException.class,()->{
            memberAdaptor.deleteMember(5l);
        });

    }

    @Test
    void registerMember() {
    }

    @Test
    void withdraw() {
    }

    @Test
    void updateMember() {
    }

}