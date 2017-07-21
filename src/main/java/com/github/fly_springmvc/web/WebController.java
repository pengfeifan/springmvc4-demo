package com.github.fly_springmvc.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 使用@Controller注解声明是一个控制器 
 * 使用@RequestMapping配置URL和方法之间的映射
 * 
 * @author william
 *
 */
@Controller
public class WebController {

	@RequestMapping("/index")
	public String home() {
		return "index";
	}
}
