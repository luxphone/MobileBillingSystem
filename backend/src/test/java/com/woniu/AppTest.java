package com.woniu;

import com.woniu.utils.JwtUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AppTest {
    @Test
    void contextLoads() {
//        String token = JwtUtils.generateToken("zy");
//        System.out.println(token);
//        String usernameFromToken = JwtUtils.getUsernameFromToken("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ6eSIsImlhdCI6MTY4NTc3NTQ1NiwiZXhwIjoxNjg1ODYxODU2fQ.vk2uSNDZlF2ptkgCRE-xmcofxWL5so530IHDb2IEABbIHOa0L9s98kI86HP-s5M25OznePFivy023W1l3EWiwA");
//        System.out.println(usernameFromToken);
        boolean b = JwtUtils.validateToken("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ6eSIsImlhdCI6MTY4NTc3NTQ1NiwiZXhwIjoxNjg1ODYxODU2fQ.vk2uSNDZlF2ptkgCRE-xmcofxWL5so530IHDb2IEABbIHOa0L9s98kI86HP-s5M25OznePFivy023W1l3EWiwA");
        System.out.println(b);

    }
}
