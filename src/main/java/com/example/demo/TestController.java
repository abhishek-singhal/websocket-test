package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;

	@Autowired
	private UserRepository userRepository;

	@GetMapping(value = "/test")
	public String test() {
		return "Hello";
	}

	@GetMapping(value = "/test2")
	public void test2() {
		simpMessagingTemplate.convertAndSend("/queue/output", "Testing1234");
	}

	@MessageMapping("/input")
	public void websocketTest(String input) {
		System.out.println(input);
	}

	@GetMapping(value = "/users")
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
}
