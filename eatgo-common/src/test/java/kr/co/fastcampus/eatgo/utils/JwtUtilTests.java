package kr.co.fastcampus.eatgo.utils;

import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;

class JwtUtilTests {

    @Test
    public void createToken(){
        JwtUtil jwtUtil = new JwtUtil();

        String token = jwtUtil.createToken(1004L, "John");

        assertThat(token, containsString("."));
    }

}