package com.github.fly_springmvc.web.demo03;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadController {

	/*
	 * 使用MutipartFile接受上传的文件
	 * 使用FileUtils.writeByteArrayToFile快速写文件到磁盘
	 */
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	@ResponseBody
	public String upload(MultipartFile file) {
		try {
			FileUtils.writeByteArrayToFile(new File("d:/upload/" + file.getOriginalFilename()), file.getBytes());
			return "ok";
		} catch (Exception e) {
			e.printStackTrace();
			return "wrong";
		}
	}
}
