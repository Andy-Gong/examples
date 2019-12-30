package redis.distributed.lock.example;

import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RReadWriteLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.concurrent.TimeUnit;

@Slf4j
public class ReadWriteLockExample {

    public static void main(String[] args) throws InterruptedException {
        final Config config = new Config();
        config.useSingleServer().setTimeout(1000).setAddress("redis://127.0.0.1:6379");
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                try {
                    final RedissonClient redisson = Redisson.create(config);
                    final RReadWriteLock readWriteLock = redisson.getReadWriteLock("readWriteLock");
                    RLock readLock = readWriteLock.readLock();
                    RLock writeLock = readWriteLock.writeLock();
                    boolean hasAquiredReadLock = readLock.tryLock(1000, 10000, TimeUnit.MILLISECONDS);
                    boolean hasAquiredWriteLock = writeLock.tryLock(1000, 10000, TimeUnit.MILLISECONDS);
                    if (hasAquiredReadLock) {
                        log.info("t1 acquired read lock");
                    } else {
                        log.info("t1 can't acquire read lock");
                    }
                    if (hasAquiredWriteLock) {
                        log.info("t1 acquired write lock");
                    } else {
                        log.info("t1 can't acquire write lock");
                    }
                } catch (Exception e) {
                    log.error("t1 exception", e);
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            public void run() {
                try {
                    final RedissonClient redisson = Redisson.create(config);
                    final RReadWriteLock readWriteLock = redisson.getReadWriteLock("readWriteLock");
                    RLock readLock = readWriteLock.readLock();
                    RLock writeLock = readWriteLock.writeLock();
                    boolean hasAquiredReadLock = readLock.tryLock(1000, 10000, TimeUnit.MILLISECONDS);
                    boolean hasAquiredWriteLock = writeLock.tryLock(1000, 10000, TimeUnit.MILLISECONDS);
                    if (hasAquiredReadLock) {
                        log.info("t2 acquired read lock");
                    } else {
                        log.info("t2 can't acquire read lock");
                    }
                    if (hasAquiredWriteLock) {
                        log.info("t2 acquired write lock");
                    } else {
                        log.info("t2 can't acquire write lock");
                    }
                } catch (Exception e) {
                    log.error("t1 exception", e);
                }
            }
        });
        t1.start();
        t2.start();
    }
}
