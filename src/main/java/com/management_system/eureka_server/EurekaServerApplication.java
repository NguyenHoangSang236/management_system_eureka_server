package com.management_system.eureka_server;

import com.management_system.utilities.utils.InitiateConfigUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.event.EventListener;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {
	@Autowired
    InitiateConfigUtils initiateConfigUtils;

	public static void main(String[] args) {
		SpringApplication.run(EurekaServerApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void initSslConfig() {
		initiateConfigUtils.initSslConfig();
	}
}
