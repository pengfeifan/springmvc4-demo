package com.github.fly_springmvc.web.demo04;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.fly_springmvc.domain.DemoDomain;

@Controller
public class ConverterController {

	@RequestMapping(value="/convert",produces={"application/x-demotype"})
	@ResponseBody
	public DemoDomain convert(@RequestBody DemoDomain domainObj){
		return domainObj;
	}
}
