package kr.co.fastcampus.eatgo.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTests {

    @Test
    public void creation(){
        User user = User.builder()
                .email("test@example.com")
                .name("tester")
                .level(10L)
                .build();

        assertEquals(user.getName(), "tester");
        assertEquals(user.isAdmin(), true);
        assertEquals(user.isActive(), true);

        user.deactivate();

        assertEquals(user.isActive(), false);
    }

}