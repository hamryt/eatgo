package kr.co.fastcampus.eatgo.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTests {

    @Test
    public void creation(){
        Category category = Category.builder().name("Korean").build();

        assertEquals(category.getName(), "Korean");
    }

}