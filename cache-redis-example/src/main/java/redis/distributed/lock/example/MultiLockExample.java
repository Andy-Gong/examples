package redis.distributed.lock.example;

import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.concurrent.TimeUnit;

@Slf4j
public class MultiLockExample {

    public static void main(String[] args) throws InterruptedException {
        Config config = new Config();
        config.useSingleServer().setTimeout(1000).setAddress("redis://127.0.0.1:6379");
        final RedissonClient redisson = Redisson.create(config);
        //the RLock may belong to different redisson instance
        final RLock lock1 = redisson.getLock("lock-example-1");
        final RLock lock2 = redisson.getLock("lock-example-2");
        final RLock lock3 = redisson.getLock("lock-example-3");
        final RLock multiLock = redisson.getMultiLock(lock1, lock2, lock3);
        Thread t = new Thread(new Runnable() {
            public void run() {
                try {
                    boolean hasAcquiredLock = multiLock.tryLock(1000, 10000, TimeUnit.MILLISECONDS);
                    log.info("lock successfully t1, hasAcquiredLock: " + hasAcquiredLock);
                    if (!hasAcquiredLock) {
                        Thread.sleep(10000);
                        hasAcquiredLock = multiLock.tryLock(1000, 10000, TimeUnit.MILLISECONDS);
                        log.info("after sleep 10000 ms, lock successfully t1, hasAcquiredLock: " + hasAcquiredLock);
                    }
                } catch (Exception e) {
                    log.info("lock exception t1," + e.getMessage());
                    e.printStackTrace();
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            public void run() {
                try {
                    boolean hasAcquiredLock = multiLock.tryLock(1000, 10000, TimeUnit.MILLISECONDS);
                    log.info("lock successfully t2, hasAcquiredLock: " + hasAcquiredLock);
                    if (!hasAcquiredLock) {
                        Thread.sleep(10000);
                        hasAcquiredLock = multiLock.tryLock(1000, 10000, TimeUnit.MILLISECONDS);
                        log.info("after sleep 10000 ms, lock successfully t2, hasAcquiredLock: " + hasAcquiredLock);
                    }
                } catch (Exception e) {
                    log.info("lock exception t2," + e.getMessage());
                    e.printStackTrace();
                }
            }
        });
        Thread t3 = new Thread(new Runnable() {
            public void run() {
                try {
                    boolean hasAcquiredLock = multiLock.tryLock(1000, 10000, TimeUnit.MILLISECONDS);
                    log.info("lock successfully t3, hasAcquiredLock: " + hasAcquiredLock);
                    if (!hasAcquiredLock) {
                        Thread.sleep(10000);
                        hasAcquiredLock = multiLock.tryLock(1000, 10000, TimeUnit.MILLISECONDS);
                        log.info("after sleep 10000 ms, lock successfully t3, hasAcquiredLock: " + hasAcquiredLock);
                    }
                } catch (Exception e) {
                    log.info("lock exception t2," + e.getMessage());
                    e.printStackTrace();
                }
            }
        });
        t.start();
        t2.start();
        t3.start();
    }
}
