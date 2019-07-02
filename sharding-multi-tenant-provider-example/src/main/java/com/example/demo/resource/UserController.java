package com.example.demo.resource;

import com.example.demo.model.User;
import com.example.demo.presentation.UserVo;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping(ResourceUtil.BASE_PATH + "/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(method = RequestMethod.POST)
    public void save(@RequestBody UserVo userVo) {
        User user = User.builder().name(userVo.getName()).region(userVo.getRegion()).build();
        userRepository.save(user);
    }
}
