package kr.co.fastcampus.eatgo.application;

import kr.co.fastcampus.eatgo.domain.Restaurant;
import kr.co.fastcampus.eatgo.domain.RestaurantNotFoundException;
import kr.co.fastcampus.eatgo.domain.RestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

class RestaurantServiceTest {

    private  RestaurantService restaurantService;

    @Mock
    private RestaurantRepository restaurantRepository;


    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this); // @mock 처리 된 변수에 적절한 객체를 연결해줌

        mockRestaurantRepository();

        restaurantService = new RestaurantService(restaurantRepository);
    }

    private void mockRestaurantRepository() {
        List<Restaurant> restaurants = new ArrayList<>();
        Restaurant restaurant = Restaurant.builder()
                .id(1004L)
                .categoryId(1L)
                .name("Bob zip")
                .address("Seoul")
                .build();

        restaurants.add(restaurant);

        given(restaurantRepository.findAll()).willReturn(restaurants);
        given(restaurantRepository.findById(1004L)).willReturn(java.util.Optional.of(restaurant));
    }

    @Test
    public void getRestaurants(){
        List<Restaurant> restaurants = restaurantService.getRestaurants();

        Restaurant restaurant = restaurants.get(0);
        assertEquals(restaurant.getId(), 1004L);
    }

    @Test
    public void getRestaurantWithExisted(){
        Restaurant restaurant = restaurantService.getRestaurantById(1004L);

        assertEquals(restaurant.getId(), 1004L);
    }

    @Test
    public void getRestaurantWithNotExisted(){
        RestaurantNotFoundException restaurantNotFoundException
                = assertThrows(RestaurantNotFoundException.class,
                                    () -> restaurantService.getRestaurantById(404L));

        assertEquals("Could not find restaurant 404", restaurantNotFoundException.getMessage());

    }

    @Test
    public void addRestaurant(){

        given(restaurantRepository.save(any())).will(invocation -> {
           Restaurant restaurant = invocation.getArgument(0);
           restaurant.setId(1234L);
           return restaurant;
        });

        Restaurant restaurant = Restaurant.builder()
                .name("Dongho")
                .address("Seoul")
                .build();

        Restaurant saved = Restaurant.builder()
                .id(1234L)
                .name("Dongho")
                .address("Seoul")
                .build();

        Restaurant created = restaurantService.addRestaurant(restaurant);

        assertEquals(created.getId(), 1234L);
    }

    @Test
    public void updateRestaurant(){
        Restaurant restaurant = Restaurant.builder()
                .id(1004L)
                .name("jason")
                .address("Ulsan")
                .build();

        given(restaurantRepository.findById(1004L)).willReturn(Optional.of(restaurant));

        restaurantService.updateRestaurant(1004L, "julie", "junju");

        assertEquals(restaurant.getName(), "julie");
        assertEquals(restaurant.getAddress(), "junju");
    }

}