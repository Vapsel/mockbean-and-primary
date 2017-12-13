package com.example.mockbeanandprimary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

@SpringBootApplication
public class MockbeanAndPrimaryApplication {
	public static void main(String[] args) {
		SpringApplication.run(MockbeanAndPrimaryApplication.class, args);
	}
}

@Controller
class SomeController {
    private final SomeService someService;

    @Autowired
    SomeController(SomeService someService) {
        this.someService = someService;
    }

	@Override public String toString() {
		return someService.toString();
	}
}

interface SomeService {

}

@Service
@Primary
@RefreshScope
class SomeServiceImpl implements SomeService {

}

@Service
class SomeOtherServiceImpl implements SomeService {

}