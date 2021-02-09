package kr.co.fastcampus.eatgo.application;

import kr.co.fastcampus.eatgo.domain.User;
import kr.co.fastcampus.eatgo.domain.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

class UserServiceTests {

    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        userService = new UserService(userRepository);
    }

    @Test
    public void getUsers(){
        List<User> mockUser = new ArrayList<>();
        mockUser.add(User.builder()
                .name("tester")
                .email("test@example.com")
                .level(10L)
                .build());
        given(userRepository.findAll()).willReturn(mockUser);

        List<User> users = userService.getUsers();

        assertEquals(users.get(0).getName(), "tester");
    }

    @Test
    public void addUser(){
        String email = "admin@example.com";
        String name = "Administrator";

        User mockUser = User.builder().email(email).name(name).build();

        given(userRepository.save(any())).willReturn(mockUser);

        User user = userService.addUser(email, name);
        assertEquals(user.getName(), name);
        assertEquals(user.getEmail(), email);
    }

    @Test
    public void updateUser(){
        Long id = 1004L;
        String email = "admin@example.com";
        String name = "batman";
        Long level = 100L;

        User mockUser = User.builder()
                .id(id)
                .email(email)
                .name("superman")
                .level(level)
                .build();

        given(userRepository.findById(any())).willReturn(Optional.of(mockUser));

        User user = userService.updateUser(id, email, name, level);

        verify(userRepository).findById(eq(id));

        assertEquals(user.getName(), "batman");
        assertEquals(user.isAdmin(), true);
    }

    @Test
    public void deactiveUser(){

        Long id = 1004L;

        User mockUser = User.builder()
                .id(id)
                .email("admin@example.com")
                .name("batman")
                .level(100L)
                .build();

        given(userRepository.findById(any())).willReturn(Optional.of(mockUser));

        User user = userService.deactiveUser(1004L);

        verify(userRepository).findById(1004L);

        assertEquals(user.isAdmin(), false);
        assertEquals(user.isActive(), false);
    }

}