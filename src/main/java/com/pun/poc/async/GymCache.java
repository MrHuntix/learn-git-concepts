package com.pun.poc.async;

import com.pun.poc.dao.GymDao;
import com.pun.poc.models.Gym;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

@Component
public class GymCache {
    private static final Logger LOG = LoggerFactory.getLogger(GymCache.class);

    private final AtomicBoolean isCacheReloadInProgress = new AtomicBoolean(false);

    private final Map<Integer, Gym> gymCache = new ConcurrentHashMap<>();

    @Autowired
    private GymDao gymDao;

    @PostConstruct
    public void init() {
        LOG.info("loading gym data into cache");
        //loadDataIntoCache();
    }

    public void loadDataIntoCache() throws InterruptedException {
        if(!isCacheReloadInProgress.get()) {
            isCacheReloadInProgress.compareAndSet(false, true);
            LOG.info("sleeping for 10 sec");
            Thread.sleep(10000);
            gymDao.findAll().forEach(gym -> gymCache.put(gym.getId(), gym));
            LOG.info("successfully loaded {} records into cache", gymCache.size());
            isCacheReloadInProgress.compareAndSet(true, false);
        } else {
            LOG.info("cache reload already in progress");
        }
    }
}
