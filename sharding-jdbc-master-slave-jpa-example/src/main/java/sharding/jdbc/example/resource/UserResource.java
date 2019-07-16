package sharding.jdbc.example.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sharding.jdbc.example.model.User;
import sharding.jdbc.example.resource.vo.UserVO;
import sharding.jdbc.example.service.UserService;

@RestController()
@RequestMapping("/users")
public class UserResource {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST)
    public long save(@RequestBody UserVO userVO) {
        User user = User.builder()
                .account(userVO.getAccount())
                .companyId(userVO.getCompanyId())
                .userName(userVO.getUserName())
                .password(userVO.getPassword())
                .tenantId(userVO.getTenantId())
                .build();
        return userService.save(user);
    }
}
