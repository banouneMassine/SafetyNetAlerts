package com.safetyNet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SafetyNetAlertsApplication {
	private static final Logger logger = LogManager.getLogger("SafetyNetAlertsApplication");
	public static void main(String[] args) {
		logger.info("Lancement de l'application SafetyNetAlerts");
		SpringApplication.run(SafetyNetAlertsApplication.class, args);
	}

}
