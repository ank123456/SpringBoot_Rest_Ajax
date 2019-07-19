package com.app.data.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {

	private final Logger logger = LoggerFactory.getLogger(AppController.class);

	@GetMapping("/")
	public String index() {
		return "ajax";
	}

}