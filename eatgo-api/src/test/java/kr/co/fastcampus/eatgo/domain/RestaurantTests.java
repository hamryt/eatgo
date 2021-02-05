package kr.co.fastcampus.eatgo.domain;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.*;

class RestaurantTests {

    @Test
    public void creation(){
        Restaurant restaurant = Restaurant.builder()
                .id(1004L)
                .name("bob")
                .address("Seoul")
                .build();

        assertEquals(restaurant.getId(), 1004L);
        assertEquals(restaurant.getName(), "bob");
        assertEquals(restaurant.getAddress(), "Seoul");
    }

    @Test
    public void information(){
        Restaurant restaurant = new Restaurant(1004L, "bob", "Seoul");

        restaurant.setName("jason");

        assertEquals(restaurant.getInformation(), "jason in Seoul");
    }

}