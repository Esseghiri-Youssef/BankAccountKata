package com.BankingCata;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.BankingCata.dtos.BankAccountDTO;
import com.BankingCata.dtos.CurrentBankAccountDTO;
import com.BankingCata.dtos.CustomerDTO;
import com.BankingCata.dtos.SavingBankAccountDTO;
import com.BankingCata.exceptions.CustomerNotFoundException;
import com.BankingCata.services.BankAccountService;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class BankingCataApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankingCataApplication.class, args);
	}
	
	@Bean
	public Docket postsApi() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage("com.BankingCata.controllers")).build();
	}
	
	 @Bean
	    CommandLineRunner commandLineRunner(BankAccountService bankAccountService){
	        return args -> {
	           Stream.of("Youssef","David","Lina").forEach(name->{
	               CustomerDTO customer=new CustomerDTO();
	               customer.setName(name);
	               customer.setEmail(name+"@gmail.com");
	               bankAccountService.saveCustomer(customer);
	           });
	           bankAccountService.listCustomers().forEach(customer->{
	               try {
	                   bankAccountService.saveCurrentBankAccount(Math.random()*90000,9000,customer.getId());
	                   bankAccountService.saveSavingBankAccount(Math.random()*120000,5.5,customer.getId());

	               } catch (CustomerNotFoundException e) {
	                   e.printStackTrace();
	               }
	           });
	            List<BankAccountDTO> bankAccounts = bankAccountService.bankAccountList();
	            for (BankAccountDTO bankAccount:bankAccounts){
	                for (int i = 0; i <10 ; i++) {
	                    String accountId;
	                    if(bankAccount instanceof SavingBankAccountDTO){
	                        accountId=((SavingBankAccountDTO) bankAccount).getId();
	                    } else{
	                        accountId=((CurrentBankAccountDTO) bankAccount).getId();
	                    }
	                    bankAccountService.deposit(accountId,10000+Math.random()*120000,"deposit");
	                    bankAccountService.withdrawal(accountId,1000+Math.random()*9000,"withdrawal");
	                }
	            }
	        };
	    }
}
