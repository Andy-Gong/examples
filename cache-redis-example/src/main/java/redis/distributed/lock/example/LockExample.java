package redis.distributed.lock.example;

import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

@Slf4j
public class LockExample {

    public static void main(String[] args) {
        Config config = new Config();
        config.useSingleServer().setTimeout(1000).setAddress("redis://127.0.0.1:6379");
        final RedissonClient redisson = Redisson.create(config);
        Thread t = new Thread(new Runnable() {
            public void run() {
                RLock rLock = null;
                try {
                    rLock = redisson.getLock("lock-example");
                    rLock.lock();
                    log.info("lock successfully t1");
                    Thread.sleep(10000);
                } catch (Exception e) {
                    log.info("lock exception t1," + e.getMessage());
                    e.printStackTrace();
                } finally {
                    if (rLock != null) {
                        rLock.unlock();
                    }
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            public void run() {
                RLock rLock = null;
                try {
                    rLock = redisson.getLock("lock-example");
                    rLock.lock();
                    log.info("lock successfully t2");
                    Thread.sleep(10000);
                } catch (Exception e) {
                    log.info("lock exception t2," + e.getMessage());
                    e.printStackTrace();
                } finally {
                    if (rLock != null) {
                        rLock.unlock();
                    }
                }
            }
        });
        t.start();
        t2.start();
    }
}
