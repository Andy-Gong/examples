package redis.distributed.lock.example;

import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.concurrent.TimeUnit;

/**
 * In this example, there are 3 threads will acquire the lock with name 'lock-example', ONLY one thread will acquire the lock.
 *
 * The lock is hold 10000 milliseconds, the other 2 threads which can't acquire the lock will sleep 10000 milliseconds, then they content to acquire the lock,
 * and only one can acquire the lock.
 */
@Slf4j
public class TryLockExample {

    public static void main(String[] args) throws InterruptedException {
        Config config = new Config();
        config.useSingleServer().setTimeout(1000).setAddress("redis://127.0.0.1:6379");
        final RedissonClient redisson = Redisson.create(config);
        Thread t = new Thread(new Runnable() {
            public void run() {
                RLock rLock = null;
                try {
                    rLock = redisson.getLock("lock-example");
                    boolean hasAcquiredLock = rLock.tryLock(1000, 10000, TimeUnit.MILLISECONDS);
                    log.info("lock successfully t1, hasAcquiredLock: " + hasAcquiredLock);
                    if (!hasAcquiredLock) {
                        Thread.sleep(10000);
                        hasAcquiredLock = rLock.tryLock(1000, 10000, TimeUnit.MILLISECONDS);
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
                RLock rLock = null;
                try {
                    rLock = redisson.getLock("lock-example");
                    boolean hasAcquiredLock = rLock.tryLock(1000, 10000, TimeUnit.MILLISECONDS);
                    log.info("lock successfully t2, hasAcquiredLock: " + hasAcquiredLock);
                    if (!hasAcquiredLock) {
                        Thread.sleep(10000);
                        hasAcquiredLock = rLock.tryLock(1000, 10000, TimeUnit.MILLISECONDS);
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
                RLock rLock = null;
                try {
                    rLock = redisson.getLock("lock-example");
                    boolean hasAcquiredLock = rLock.tryLock(1000, 10000, TimeUnit.MILLISECONDS);
                    log.info("lock successfully t3, hasAcquiredLock: " + hasAcquiredLock);
                    if (!hasAcquiredLock) {
                        Thread.sleep(10000);
                        hasAcquiredLock = rLock.tryLock(1000, 10000, TimeUnit.MILLISECONDS);
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
