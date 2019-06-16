package sharding.jdbc.example;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sharding.jdbc.example.model.Order;
import sharding.jdbc.example.model.User;
import sharding.jdbc.example.service.OrderService;
import sharding.jdbc.example.service.UserService;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ShardingTest {

    private final static Integer[] TENANT_IDS = new Integer[]{0, 1};

    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;

    @Test
    public void testInsertUserIntoDs0() {
        User user = User.builder()
                .tenantId(TENANT_IDS[0])
                .companyId(BigInteger.valueOf(new Random().nextLong()))
                .userName(UUID.randomUUID().toString())
                .account(UUID.randomUUID().toString())
                .password("test")
                .build();
        userService.save(user);
        System.out.println(user.getUserId());
    }

    @Test
    public void testInsertUserIntoDs1() {
        User user = User.builder()
                .tenantId(TENANT_IDS[1])
                .companyId(BigInteger.valueOf(new Random().nextLong()))
                .userName(UUID.randomUUID().toString())
                .account(UUID.randomUUID().toString())
                .password("test")
                .build();
        userService.save(user);
        System.out.println(user.getUserId());
    }

    @Test
    public void testInsertOrderIntoDs0() {
        User user = User.builder()
                .tenantId(TENANT_IDS[0])
                .companyId(BigInteger.valueOf(new Random().nextLong()))
                .userName(UUID.randomUUID().toString())
                .account(UUID.randomUUID().toString())
                .password("test")
                .build();
        userService.save(user);
        Order order = Order.builder()
                .tenantId(TENANT_IDS[0])
                .userId(user.getUserId())
                .orderName(UUID.randomUUID().toString())
                .createTime(new Timestamp(System.currentTimeMillis()))
                .build();
        orderService.save(order);
        System.out.println(order.getUserId());
    }

    @Test
    public void testInsertOrderIntoDs1() {
        User user = User.builder()
                .tenantId(TENANT_IDS[1])
                .companyId(BigInteger.valueOf(new Random().nextLong()))
                .userName(UUID.randomUUID().toString())
                .account(UUID.randomUUID().toString())
                .password("test")
                .build();
        userService.save(user);
        Order order = Order.builder()
                .tenantId(TENANT_IDS[1])
                .userId(user.getUserId())
                .orderName(UUID.randomUUID().toString())
                .createTime(new Timestamp(System.currentTimeMillis()))
                .build();
        orderService.save(order);
        System.out.println(order.getUserId());
    }
}
