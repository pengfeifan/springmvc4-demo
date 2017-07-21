package com.github.fly_springmvc.web.fortest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.fly_springmvc.service.DemoService;

@RestController
public class Rest4TestController {

	@Autowired
	private DemoService demoService;

	@RequestMapping(value = "/testRest", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String testRest() {
		return demoService.saySomething();
	}
}
