package com.pun.poc.common;

import com.pun.poc.async.GymCache;
import com.pun.poc.dao.GymDao;
import com.pun.poc.models.Gym;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class DataReader implements ApplicationListener<ApplicationStartedEvent> {
    Logger LOG = LoggerFactory.getLogger(DataReader.class);

    @Autowired
    GymDao gymDao;

    @Autowired
    GymCache gymCache;

    boolean createGymRecords = false;

    private ExecutorService executor;

    public DataReader() {
        LOG.info("constructor");
    }

    @PostConstruct
    public void init() {
        LOG.info("post construct");
        executor = Executors.newCachedThreadPool();
    }

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        LOG.info("application started");
        for(int i=0;i<3;i++) {
            executor.submit(()->{
                try {
                    gymCache.loadDataIntoCache();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    @PreDestroy
    public void destory() {
        executor.shutdown();
    }
}

