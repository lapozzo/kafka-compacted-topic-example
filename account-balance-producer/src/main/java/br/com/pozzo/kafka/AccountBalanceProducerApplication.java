package br.com.pozzo.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableKafka
@SpringBootApplication
@EnableScheduling
public class AccountBalanceProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountBalanceProducerApplication.class, args);
	}

}
