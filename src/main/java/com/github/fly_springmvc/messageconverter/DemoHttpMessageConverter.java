package com.github.fly_springmvc.messageconverter;

import java.io.IOException;
import java.nio.charset.Charset;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.StreamUtils;

import com.github.fly_springmvc.domain.DemoDomain;

/**
 * 自定义HttpMessageConverter实现类
 * 继承AbstractHttpMessageConverter来实现HttpMessageConverter接口
 * 
 * @author william
 *
 */
public class DemoHttpMessageConverter extends AbstractHttpMessageConverter<DemoDomain> {

	public DemoHttpMessageConverter() {
		// 新建一个自定义媒体类型application/x-newtype
		super(new MediaType("application", "x-demotype", Charset.forName("UTF-8")));
	}

	/*
	 * 重写readintenal方法，处理请求的数据。
	 * 代码表明处理由"-"隔开的数据，并转成DemoDomain
	 * @see org.springframework.http.converter.AbstractHttpMessageConverter#readInternal(java.lang.Class, org.springframework.http.HttpInputMessage)
	 */
	@Override
	protected DemoDomain readInternal(Class<? extends DemoDomain> clazz, HttpInputMessage inputMsg)
			throws IOException, HttpMessageNotReadableException {
		String temp = StreamUtils.copyToString(inputMsg.getBody(), Charset.forName("UTF-8"));
		String[] tempArr = temp.split("-");
		return new DemoDomain(new Long(tempArr[0]), tempArr[1]);
	}

	/*
	 * 表明本HttpMessageConverter值处理DemoDomain这个类
	 * @see org.springframework.http.converter.AbstractHttpMessageConverter#supports(java.lang.Class)
	 */
	@Override
	protected boolean supports(Class<?> clazz) {
		return DemoDomain.class.isAssignableFrom(clazz);
	}
	/*
	 * 重写writeInternal,处理如何输出数据到response
	 * @see org.springframework.http.converter.AbstractHttpMessageConverter#writeInternal(java.lang.Object, org.springframework.http.HttpOutputMessage)
	 */
	@Override
	protected void writeInternal(DemoDomain obj, HttpOutputMessage outputMsg)
			throws IOException, HttpMessageNotWritableException {
		String out = "hello: " + obj.getId() + "-*-" + obj.getName();
		outputMsg.getBody().write(out.getBytes());
	}

}
