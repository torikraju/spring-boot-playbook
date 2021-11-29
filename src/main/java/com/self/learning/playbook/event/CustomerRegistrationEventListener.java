package com.self.learning.playbook.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class CustomerRegistrationEventListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerRegistrationEventListener.class);

    @Async
    @EventListener
    public void handleRegistration(CustomerRegistrationEvent event) {
        try {
            Thread.sleep(5000L);
            LOGGER.info("Registration event got triggered for customer:: {}, Current Thread Name- {}", event.getName(), Thread.currentThread().getName());
        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage());
        }
    }

}
