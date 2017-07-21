package com.github.fly_springmvc.web.demo02;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.fly_springmvc.domain.DemoDomain;

@Controller
public class AdviceController {

	@RequestMapping("/advice")
	public String getSomething(@ModelAttribute("msg") String msg,DemoDomain obj){
		throw new IllegalArgumentException("非常抱歉，参数有误!来自@ModelAttibute:"+msg);
	}
}
