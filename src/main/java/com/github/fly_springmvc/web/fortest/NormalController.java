package com.github.fly_springmvc.web.fortest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.fly_springmvc.service.DemoService;

@Controller
public class NormalController {

	@Autowired
	private DemoService demoService;

	@RequestMapping("/normal")
	public String testPage(Model model) {
		model.addAttribute("msg", demoService.saySomething());
		return "page";
	}
}
