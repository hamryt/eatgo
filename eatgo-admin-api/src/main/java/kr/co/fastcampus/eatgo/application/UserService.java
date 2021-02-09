package kr.co.fastcampus.eatgo.application;

import kr.co.fastcampus.eatgo.domain.User;
import kr.co.fastcampus.eatgo.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        List<User> users = userRepository.findAll();

        return users;
    }

    public User addUser(String email, String name) {
        User newUser = User.builder()
                .name(name)
                .level(1L)
                .email(email)
                .build();

        return userRepository.save(newUser);
    }

    @Transactional
    public User updateUser(Long id, String email, String name, Long level) {
        //todo: restaurant 예외 처리 참조
        User user = userRepository.findById(id).orElse(null);

        user.setName(name);
        user.setEmail(email);
        user.setLevel(level);

        return user;
    }

    @Transactional
    public User deactiveUser(Long id) {
        //todo 실제로 작업 필요
        User user = userRepository.findById(id).orElse(null);
        user.deactivate();
        return user;
    }
}
