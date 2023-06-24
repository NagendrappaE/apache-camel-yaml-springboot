package com.ece.camel.yaml.spring.controller;

import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ece.camel.yaml.spring.bean.StudentResp;


@RequestMapping
@RestController
public class StudentController {

	@PostMapping("savestudent")
	public StudentResp getStudent(HttpServletRequest request,HttpServletResponse response) {
		
		System.out.println("the IP address "+request.getRemoteAddr());

		StudentResp resp = new StudentResp();

		resp.setSage(String.valueOf(LocalDate.now()));
		resp.setSalary("500000");
		resp.setSname("NAGENDRA");
		return resp;

	}

}