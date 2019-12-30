package sharding.jdbc.example.resource;

import io.shardingsphere.core.keygen.DefaultKeyGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sharding.jdbc.example.model.User;
import sharding.jdbc.example.repository.UserRepository;
import sharding.jdbc.example.resource.vo.UserVO;
import sharding.jdbc.example.service.UserService;

@RestController()
@RequestMapping("/users")
public class UserResource {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    private DefaultKeyGenerator defaultKeyGenerator = new DefaultKeyGenerator();

    @RequestMapping(method = RequestMethod.POST)
    public long save(@RequestBody UserVO userVO) {
        User user = User.builder()
                .userId(defaultKeyGenerator.generateKey().longValue())
                .account(userVO.getAccount())
                .companyId(userVO.getCompanyId())
                .userName(userVO.getUserName())
                .password(userVO.getPassword())
                .tenantId(userVO.getTenantId())
                .build();
        return userService.save(user);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id:.+}")
    public UserVO get(@PathVariable("id") Long id) {
        User user = userRepository.findByUserId(id);
        return UserVO.builder()
                .account(user.getAccount())
                .companyId(user.getCompanyId())
                .password(user.getPassword())
                .tenantId(user.getTenantId())
                .userId(user.getUserId())
                .userName(user.getUserName())
                .build();
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id:.+}")
    public void update(@PathVariable("id") Long id, @RequestBody UserVO userVO) {
        User user = userRepository.findByUserId(id);
        user.setUserName(userVO.getUserName());
        userRepository.save(user);
    }
}
