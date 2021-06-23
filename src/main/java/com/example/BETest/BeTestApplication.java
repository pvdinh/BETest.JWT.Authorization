package com.example.BETest;

import com.example.BETest.object.AccountCredentials;
import com.example.BETest.repository.AccountRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
@Api(value = "/login", tags = "Login", description = "Login")
@RequestMapping("/login")
public class BeTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(BeTestApplication.class, args);
	}
//
//	@GetMapping("/home")
//	public String home(){
//		return "index";
//	}

	@PostMapping
	@ApiOperation(value = "Đăng nhập để lấy token")
	public String login(@RequestBody AccountCredentials accountCredentials){
		return "authorization";
	}

//	@Autowired
//	private AccountRepository accountRepository;
//	@Bean
//	CommandLineRunner runner(AccountRepository accountRepository){
//		return args -> {
//			AccountCredentials acc = new AccountCredentials("admin","admin","ADMIN");
//
//			if(accountRepository.findAccountCredentialsByUsername("admin") != null){
//				System.out.println("already exists");
//			}else {
//				System.out.println("inserting student");
//				accountRepository.insert(acc);
//			}
//		};
//	}
}
