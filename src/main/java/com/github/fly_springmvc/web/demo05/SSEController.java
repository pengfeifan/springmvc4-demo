package com.github.fly_springmvc.web.demo05;

import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * SSE(Server Send Event 服务端发送事件)
 * 输出的媒体类型是text/event-stream,这是服务器端SSE的支持
 * 本例演示每5秒钟向浏览器推送随机消息
 * 
 * @author william
 *
 */
@Controller
public class SSEController {

	@RequestMapping(value = "/push", produces = "text/event-stream")//
	@ResponseBody
	public String push() {
		Random r = new Random();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "data:Testing 1,2,3-->" + r.nextInt() + "\n\n";
	}
}
