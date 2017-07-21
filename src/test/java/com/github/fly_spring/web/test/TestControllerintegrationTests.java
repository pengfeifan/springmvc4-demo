package com.github.fly_spring.web.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.github.fly_springmvc.AppMvcConfig;
import com.github.fly_springmvc.service.DemoService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppMvcConfig.class })
@WebAppConfiguration("src/main/resources") 
//@WebAppConfiguration注解在类上，用来声明加载的ApplicationContext是一个WebApplicationContext.
//它的属性指定是Web的资源位置，默认为src/main/webapp/.
public class TestControllerintegrationTests {

	private MockMvc mockMvc; //模拟MVC对象

	@Autowired
	private DemoService demoService; //注入Spring的Bean

	@Autowired
	WebApplicationContext wac; //注入WebApplicationContext

	@Autowired
	MockHttpSession session; //模拟http session

	@Autowired
	MockHttpServletRequest request;//模拟http request

	@Before // 在测试开始前进行的初始化操作
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();//初始化MVC对象
	}

	@Test
	public void testNormalController() throws Exception{
		mockMvc.perform(get("/normal")) //模拟向/normal进行get请求
		.andExpect(status().isOk()) //预期控制返回状态为200
		.andExpect(view().name("page"))//预期view的名称为page
		.andExpect(forwardedUrl("/WEB-INF/classes/views/page.jsp"))//预期页面转向的真正路径为/WEB-INF/classes/views/page.jsp
		.andExpect(model().attribute("msg", demoService.saySomething()));//预期model里的值是demoService.saySomething()返回值hello
	}

	@Test
	public void testRestController() throws Exception {
		mockMvc.perform(get("/testRest")).andExpect(status().isOk())
				.andExpect(content().contentType("text/plain;charset=UTF-8"))//预期返回值的媒体类型为text/plain;charset=UTF-8
				.andExpect(content().string(demoService.saySomething()));//预期返回值的内容为demoService.saySomething()返回值hello
	}

}
