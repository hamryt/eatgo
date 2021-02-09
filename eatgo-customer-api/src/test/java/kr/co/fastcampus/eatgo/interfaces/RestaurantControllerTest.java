package kr.co.fastcampus.eatgo.interfaces;

import kr.co.fastcampus.eatgo.application.RestaurantService;
import kr.co.fastcampus.eatgo.domain.MenuItem;
import kr.co.fastcampus.eatgo.domain.Restaurant;
import kr.co.fastcampus.eatgo.domain.RestaurantNotFoundException;
import kr.co.fastcampus.eatgo.domain.Review;
import kr.co.fastcampus.eatgo.interfaces.RestaurantController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class)//스프링으로 테스트
@WebMvcTest(RestaurantController.class)//test하는 가짜 객체 만듦
class RestaurantControllerTest {
    
    @Autowired
    private MockMvc mvc;

//    @SpyBean(RestaurantService.class)//괄호 안에는 해당 인터페이스 타입을 구현한 어떤 클래스를 사용할건지 명시
//    private RestaurantService restaurantService;

    @MockBean // 가짜 객체
    private RestaurantService restaurantService;
    
    @Test
    public void list() throws Exception {
        List<Restaurant> restaurants = new ArrayList<>();
        restaurants.add(Restaurant.builder()
                .id(1004L)
                .name("Bobs zip")
                .address("Seoul")
                .build());

        given(restaurantService.getRestaurants("seoul")).willReturn(restaurants);

        mvc.perform(get("/restaurants?region=seoul"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"id\":1004")
                ))
                .andExpect(content().string(
                        containsString("\"name\":\"Bobs zip\"")
                ));
    }

    @Test
    public void detailWithExisted() throws Exception {

        Restaurant restaurant = Restaurant.builder()
                .id(2020L)
                .name("Cyber Food")
                .address("Seoul")
                .build();

        MenuItem menuItem = MenuItem.builder()
                .name("Kimchi")
                .build();

        restaurant.setMenuItems(Arrays.asList(menuItem));
        Review review = Review.builder()
                .name("frank")
                .score(3)
                .description("delicious")
                .build();
        restaurant.setReviews(Arrays.asList(review));

        given(restaurantService.getRestaurantById(2020L)).willReturn(restaurant);

        mvc.perform(get("/restaurants/2020"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"id\":2020")
                ))
                .andExpect(content().string(
                        containsString("\"name\":\"Cyber Food\"")
                ))
                .andExpect(content().string(
                        containsString("Kimchi")
                ))
                .andExpect(content().string(
                        containsString("delicious")
                ));
    }

    @Test
    public void detailWithNotExisted() throws Exception {
        given(restaurantService.getRestaurantById(404L)).willThrow(new RestaurantNotFoundException(404L));

        mvc.perform(get("/restaurants/404"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("{}"));
    }

}