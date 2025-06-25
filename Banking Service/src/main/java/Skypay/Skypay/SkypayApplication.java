package Skypay.Skypay;

import Skypay.Skypay.bankingservice.Account;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SkypayApplication {

	public static void main(String[] args) {
		SpringApplication.run(SkypayApplication.class, args);
		//System.out.println("Compiled");
		Account account = new Account();
		account.deposit(50, "10-06-2025");
		account.deposit(2000, "13-06-2025");
		account.withdraw(500, "14-06-2025");
		account.printStatement();
	}

}
