package kr.co.fastcampus.eatgo.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegionTests {

    @Test
    public void creation(){
        Region region = Region.builder().name("seoul").build();

        assertEquals(region.getName(), "seoul");
    }
}