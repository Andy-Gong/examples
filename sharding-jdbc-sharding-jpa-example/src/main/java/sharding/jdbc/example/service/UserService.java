package sharding.jdbc.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sharding.jdbc.example.model.User;
import sharding.jdbc.example.repository.UserRepository;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public long save(User user) {
        userRepository.save(user);
        return user.getUserId();
    }

    public void batchSave(List<User> users) {
        userRepository.saveAll(users);
    }
}
