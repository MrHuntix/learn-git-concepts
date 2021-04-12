package com.pun.poc.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DataReader implements ApplicationListener<ApplicationStartedEvent> {
    Logger LOG = LoggerFactory.getLogger(DataReader.class);

    public DataReader() {
        LOG.info("constructor");
    }

    @PostConstruct
    public void init() {
        LOG.info("post construct");
    }

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {

        event.getSource();
        LOG.info("application started");
    }
}
