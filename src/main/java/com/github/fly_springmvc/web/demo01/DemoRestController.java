package com.github.fly_springmvc.web.demo01;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.fly_springmvc.domain.DemoDomain;

@RestController // 1.@RestController组合了@Controller和@ResponseBody
@RequestMapping("/rest")
public class DemoRestController {

	@RequestMapping(value = "/getJson", produces = { "application/json;charset=UTF-8" }) // 2.返回数据的媒体类型是json
	public DemoDomain getJson(DemoDomain obj) {
		return new DemoDomain(obj.getId() + 1, obj.getName() + "yy");// 3.直接返回对象，对象会自动转换成json
	}

	@RequestMapping(value = "/getXml", produces = { "application/xml;charset=UTF-8" }) // 4.返回数据的媒体类型是xml
	public DemoDomain getXml(DemoDomain obj) {
		return new DemoDomain(obj.getId() + 1, obj.getName() + "yy");// 5.直接返回对象，对象会自动转换成xml
	}
	
	/*
	 	http://localhost:8080/fly-springmvc4/rest/getJson/?id=1&name=hello
	 		{"id":2,"name":"helloyy"}
	 	http://localhost:8080/fly-springmvc4/rest/getXml/?id=1&name=hello
		 	<DemoDomain>
				<id>2</id>
				<name>helloyy</name>
			</DemoDomain>
	 */
}
