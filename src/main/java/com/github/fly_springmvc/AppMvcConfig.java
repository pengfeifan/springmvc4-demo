package com.github.fly_springmvc;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.github.fly_springmvc.interceptor.DemoInterceptor;
import com.github.fly_springmvc.messageconverter.DemoHttpMessageConverter;

/**
 * Spring MVC 配置 配置了一个JSP的ViewResolver,用来映射路径和实际页面的位置
 * 使用@EnableWebMvc注解会开启一些默认配置，如一些ViewResolver或者MessageConverter
 * 
 * @author william
 *
 */
@Configuration
@EnableWebMvc
@EnableScheduling
@ComponentScan("com.github.fly_springmvc")
public class AppMvcConfig extends WebMvcConfigurerAdapter {

	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/classes/views/");
		viewResolver.setSuffix(".jsp");
		viewResolver.setViewClass(JstlView.class);
		return viewResolver;
	}

	/*
	 * addResourceHandler指的是对外暴露的访问路径 
	 * addResourceLocations指的是文件放置的目录
	 * 
	 * @see
	 * org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
	 * #addResourceHandlers(org.springframework.web.servlet.config.annotation.
	 * ResourceHandlerRegistry)
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// /**的方式，表明要加载[conf]目录下包括各级子目录中的所有配置文件
		registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/assets/");
	}

	// 配置拦截器的Bean
	@Bean
	public DemoInterceptor demoInterceptor() {
		return new DemoInterceptor();
	}

	// 重写addInterceptors方法，注册拦截器
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(demoInterceptor());
	}
	
	// 路径匹配参数配置:重写configurePathMatch方法可不忽略"."后面的参数
	@Override
	public void configurePathMatch(PathMatchConfigurer configurer) {
		configurer.setUseSuffixPatternMatch(false);
	}
	
	// 快捷的ViewController:重写addViewControllers方法 页面跳转
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/index").setViewName("/index");
		/*
		 http://localhost:8080/fly-springmvc4/toUpload
		 */
		registry.addViewController("/toUpload").setViewName("/upload");// 添加转向upload页面的ViewController
		/*
		 http://localhost:8080/fly-springmvc4/converter
		 */
		registry.addViewController("/converter").setViewName("converter");
		/*
		 http://localhost:8080/fly-springmvc4/sse
		 */
		registry.addViewController("/sse").setViewName("/sse");
		registry.addViewController("/async").setViewName("/async");
	}
	
	// MultipartResolver配置
	@Bean
	public MultipartResolver multipartResolver(){
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(1000000);
		return multipartResolver;
	}
	
	@Bean
	public DemoHttpMessageConverter converter(){
		return new DemoHttpMessageConverter();
	}
	/*
	 * configureMessageConverters:重载会覆盖掉Spring MVC默认注册的多个HttpMessageConverter
	 * extendMessageConverters:仅添加一个自定义的HttpMessageConverter,不覆盖默认注册的HttpMessageConverter
	 */
	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(converter());
	}
}
