package com.bamin.woorder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WoorderApplication {

    private static final Logger log = LoggerFactory.getLogger(WoorderApplication.class);

    @Value("${spring.datasource.url}")
    private String datasourceUrl;

	public static void main(String[] args) {
		SpringApplication.run(WoorderApplication.class, args);
	}

    @Bean
    public CommandLineRunner runner() {
        return (a) -> log.info("spring.datasource.url : {}", datasourceUrl);
    }
}
