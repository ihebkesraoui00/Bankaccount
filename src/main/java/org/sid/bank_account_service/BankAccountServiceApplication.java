package org.sid.bank_account_service;

import org.sid.bank_account_service.entities.BankAccount;
import org.sid.bank_account_service.entities.Customer;
import org.sid.bank_account_service.enums.AccountType;
import org.sid.bank_account_service.repositories.BankAcoountRepository;
import org.sid.bank_account_service.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class BankAccountServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankAccountServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(BankAcoountRepository bankAccountRepository, CustomerRepository customerRepository) {
		return args -> {
			Stream.of("mohamed", "yassine", "ahmed", "asma").forEach(c -> {
				Customer customer = Customer.builder().name(c).build();
				customerRepository.save(customer);
			});

			customerRepository.findAll().forEach(customer -> {
				for (int i = 0; i < 10; i++) {
					BankAccount bankAccount = BankAccount.builder()
							.id(UUID.randomUUID().toString()) // Use UUID for ID generation
							.type(Math.random() > 0.5 ? AccountType.CUURENT_ACCOUNT : AccountType.SAVING_ACCOUNT)
							.balance(10000 + Math.random() * 9000)
							.createAt(new Date())
							.currency("MAD")
							.customer(customer)
							.build();

					bankAccountRepository.save(bankAccount);
				}
			});
		};
	}
}
