package com.springboot.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/test")
public class TestController {

	@RequestMapping(value = "/getdate", produces = "text/event-stream;charset=UTF-8")
	public String getDate(HttpServletResponse response) throws IOException {
		String result = "data:" + new Date() + "\n\n";
		return result;
	}
}
