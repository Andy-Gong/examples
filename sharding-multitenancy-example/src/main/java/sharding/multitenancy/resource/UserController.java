package sharding.multitenancy.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sharding.multitenancy.model.User;
import sharding.multitenancy.presentation.UserVo;
import sharding.multitenancy.repository.UserRepository;

@RestController(value = ResourceUtil.BASE_PATH + "/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(method = RequestMethod.POST)
    public void save(@RequestBody UserVo userVo) {
        User user = User.builder().name(userVo.getName()).region(userVo.getRegion()).build();
        userRepository.save(user);
    }
}
