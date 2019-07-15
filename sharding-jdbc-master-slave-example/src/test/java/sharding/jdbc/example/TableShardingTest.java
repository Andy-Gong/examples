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
 * table sharding test case. And the table sharding strategy is that the table name is table_id%2
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TableShardingTest {

    private final static Integer[] TENANT_IDS = new Integer[]{0, 1};

    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;

    /**
     * use case: when user's tenant id is 0, the target database is ds0.
     * If user id is odd, the target table is user_1.
     * if user id is even, the target table is user_0.
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
     * use case: when user's tenant id is 0, the target database is ds1.
     * If user id is odd, the target table is user_1.
     * if user id is even, the target table is user_0.
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
     * use case: when order's tenant id is 0, the target database is ds0.
     * If order id is odd, the target table is order_1.
     * if order id is even, the target table is order_0.
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
        System.out.println(order.getOrderId());
    }

    /**
     * use case: when order's tenant id is 0, the target database is ds1.
     * If order id is odd, the target table is order_1.
     * if order id is even, the target table is order_0.
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
        System.out.println(order.getOrderId());
    }
}
