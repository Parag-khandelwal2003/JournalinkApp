package net.apmoller.journalink;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class JournalLinkApplication {

	public static void main(String[] args) {

		SpringApplication.run(JournalLinkApplication.class, args);
	}
}
