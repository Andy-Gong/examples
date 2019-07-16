package sharding.jdbc.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sharding.jdbc.example.mapper.UserMapper;
import sharding.jdbc.example.model.User;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public int save(User user) {
        return userMapper.save(user);
    }

    public int batchSave(List<User> users) {
        return userMapper.batchSave(users);
    }
}
