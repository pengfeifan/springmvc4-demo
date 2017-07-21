package com.github.fly_springmvc.web.demo01;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.fly_springmvc.domain.DemoDomain;

@Controller // 1.声明此类是一个控制器
@RequestMapping("/anno") // 2.映射此类的访问路径是/anno
public class DemoAnnoController {

	@RequestMapping(produces = "text/plain;charset=UTF-8") // 3.此方法未标注路径，因此使用类级别的路径/anno;produces可定制返回的response的媒体类型和字符集，或需返回值是json对象
	@ResponseBody
	public String index(HttpServletRequest request) { // 4.HttpServletRequest和HttpServletRsponse可作为参数
		return "url:" + request.getRequestURL() + " can access!";
	}

	@RequestMapping(value = "/pathvar/{str}", produces = "text/plain;charset=UTF-8") // 5.接受路径参数,并在方法参数前结合@PathVariable使用
	@ResponseBody
	public String demoPathVar(@PathVariable String str, HttpServletRequest request) {
		return "url:" + request.getRequestURL() + "can Access!the value of str:" + str;
	}

	@RequestMapping(value = "/requestParam", produces = "text/plain;charset=UTF-8") // 6.常规参数
	public @ResponseBody String passRequestParam(Long id, HttpServletRequest request) {
		return "url:" + request.getRequestURL() + "can Access!id:" + id;
	}

	@RequestMapping(value = "obj", produces = "application/json;charset=UTF-8") // 7.对象参数
	@ResponseBody // 8
	public String passObj(DemoDomain obj, HttpServletRequest request) {
		return "url:" + request.getRequestURI() + "can Access!obj id:" + obj.getId() + ",obj name:" + obj.getName();
	}

	@RequestMapping(value = { "/name1", "/name2" }, produces = "text/plain;charset=UTF-8") // 9.映射不同的路径到相同的方法，访问路径为/anno/name1或 /anno/name2
	@ResponseBody
	public String remove(HttpServletRequest request) {
		return "url:" + request.getRequestURL() + "can access!";
	}
	
	/*
	 http://localhost:8080/fly-springmvc4/anno/pathvar/hello
	 	url:http://localhost:8080/fly-springmvc4/anno/pathvar/hellocan Access!the value of str:hello
	 */
}
