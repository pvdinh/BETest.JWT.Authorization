package com.example.BETest;

import com.example.BETest.object.AccountCredentials;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
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
//	@Bean
//	CommandLineRunner runner(StudentRepository studentRepository){
//		Address address=new Address(
//				"Vet Nam",
//				"Nam Dinh",
//				"700000"
//		);
//		return args -> {
//			Student student = new Student(
//					"Pham",
//					"Dinh",
//					"phamvandinhmta@gmail.com",
//					Gender.MALE,
//					address,
//					Arrays.asList("Toan","Van","Anh"),
//					BigDecimal.TEN,
//					LocalDateTime.now()
//			);
//
//			if(studentRepository.findStudentByEmail("phamvandinhmta@gmail.com").size() > 0){
//				System.out.println("already exists");
//			}else {
//				System.out.println("inserting student");
//				studentRepository.insert(student);
//			}
//		};
//	}
}
