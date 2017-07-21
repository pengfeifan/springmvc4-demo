package com.github.fly_springmvc.web.demo05;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

import com.github.fly_springmvc.service.PushService;

@Controller
public class AysncController {

	@Autowired
	private PushService pushService;// 定时任务,定时更新DefferredResult
	
	@RequestMapping("/defer")
	@ResponseBody
	public DeferredResult<String> deferredCall(){ //返回给客户端DeferredResult
		return pushService.getAsyncUpdate();
	}
}
