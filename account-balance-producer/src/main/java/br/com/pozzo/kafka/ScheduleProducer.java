package br.com.pozzo.kafka;

import java.util.Date;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduleProducer {
	Logger logger = LoggerFactory.getLogger(ScheduleProducer.class);

	@Autowired
	private TopicProducer topicProducer;

	private static final String balanceEventTemplate = "{" + "	\"account_id\":%d," + "	\"balance\": %f,"
			+ "	\"date\": \"%s\"" + "}";

	@Scheduled(fixedRate = 1000)
	public void sendBalanceEvent() {
		logger.info("Sending balance event " + new Date());
		int accountId = new Random().nextInt(5);
		String balanceEvent = generateBalanceEvent(accountId);
		topicProducer.send(String.valueOf(accountId), balanceEvent);
	}

	private String generateBalanceEvent(int accountId) {
		double balance = new Random().nextDouble()*100;
		String currentDate = new Date().toString();
		return String.format(balanceEventTemplate, accountId, balance, currentDate);
	}

	public static void main(String[] args) {
		System.out.println(String.format(balanceEventTemplate, 10, 14, 10));
	}
}
