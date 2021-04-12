package com.pun.poc.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.boot.web.server.GracefulShutdownResult;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class KillSwitch implements ApplicationListener<WebServerInitializedEvent> {
    Logger LOG = LoggerFactory.getLogger(KillSwitch.class);

    private WebServerInitializedEvent event;

    @Override
    public void onApplicationEvent(WebServerInitializedEvent event) {
        setEvent(event);
    }

    public void kill() {
        LOG.info("kill initiatated for {}", event.getApplicationContext().getApplicationName());
        event.getSource().shutDownGracefully(this::shutdownComplete);
    }

    public void setEvent(WebServerInitializedEvent event) {
        this.event = event;
    }

    private void shutdownComplete(GracefulShutdownResult result) {
        LOG.info("status during shutdown {}", result.name());
    }
}
