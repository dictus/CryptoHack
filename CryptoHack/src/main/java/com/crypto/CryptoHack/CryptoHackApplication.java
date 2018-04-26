package com.crypto.CryptoHack;

import com.crypto.CryptoHack.dto.CryptoAPIClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableAutoConfiguration(exclude = DataSourceAutoConfiguration.class)
public class CryptoHackApplication {

	public static void main(String[] args) {
		SpringApplication.run(CryptoHackApplication.class, args);
	}
}
