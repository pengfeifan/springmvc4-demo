package com.github.fly_springmvc.advice;

import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

/**
 * 定制ControllerAdvice
 * 使用@ControllerAdvice声明一个控制器建言，@ControllerAdvice组合了@Component注解，自动注册为Spring的Bean
 * @author william
 *
 */
@ControllerAdvice
public class ExceptionHandleAdvice {
	
	/*
	 * @ExceptionHandler定义全局处理
	 * 通过@ExceptionHandler的value属性可过滤拦截的条件，此处可以看出我们拦截所有的Exception
	 */
	@ExceptionHandler(value=Exception.class)
	public ModelAndView exception(Exception exception, WebRequest request){
		ModelAndView modelAndView = new ModelAndView("error");//
		modelAndView.addObject("errorMessage",exception.getMessage());
		return modelAndView;
	}
	/*
	 * 使用@ModelAttribute注解将键值对添加到全局，所有注解的@RequestMapping的方法可获得此键值对
	 */
	@ModelAttribute
	public void addAttributes(Model model){
		model.addAttribute("msg","额外信息");
	}
	/*
	 * 通过@InitBinder注解定制WebDataBinder
	 */
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder){
		webDataBinder.setDisallowedFields("id");
	}

}
