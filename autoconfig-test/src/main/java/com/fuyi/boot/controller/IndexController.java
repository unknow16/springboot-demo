package com.fuyi.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fuyi.starter.FuyiService;

@RestController
public class IndexController {

	@Autowired
	private FuyiService fuyiService;
	
	@RequestMapping("/index")
	public String index(String domain) {
		return fuyiService.wrap(domain);
	}
}
