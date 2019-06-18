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
import java.util.Random;
import java.util.UUID;

/**
 * database sharding test case, the database sharding strategy is tenant id.
 * If the tenant id is 0, the database is ds0.
 * If the tenant id is 1, the database is ds1.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class DatabaseShardingTest {

    private final static Integer[] TENANT_IDS = new Integer[]{0, 1};

    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;

    /**
     * use case: when user's tenant id is 0, the user is inserted into ds0
     */
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

    /**
     * use case: when user's tenant id is 1, the user is inserted into ds1
     */
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

    /**
     * use case: when order's tenant id is 0, the user is inserted into ds0
     */
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

    /**
     * use case: when order's tenant id is 1, the user is inserted into ds0
     */
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
