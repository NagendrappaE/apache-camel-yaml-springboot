package com.ece.camel.yaml.spring.controller;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ece.camel.yaml.spring.bean.Data;
import com.ece.camel.yaml.spring.bean.Datum;
import com.ece.camel.yaml.spring.bean.HopexResponse;
import com.ece.camel.yaml.spring.bean.OrganizationalProcess;
import com.ece.camel.yaml.spring.bean.OrganizationalProcessOwnerOrganizationalProcess;
import com.ece.camel.yaml.spring.bean.Root;
import com.ece.camel.yaml.spring.bean.StudentResp;

@RequestMapping
@RestController
public class StudentController {

	@PostMapping("savestudent")
	public StudentResp getStudent(HttpServletRequest request, HttpServletResponse response) {

		System.out.println("the IP address " + request.getRemoteAddr());

		StudentResp resp = new StudentResp();

		resp.setSage(String.valueOf(LocalDate.now()));
		resp.setSalary("500000");
		resp.setSname("NAGENDRA");
		return resp;

	}

	@PostMapping("getapex")
	public Root getApexRespo(HttpServletRequest request, HttpServletResponse response) {

		System.out.println("the IP address " + request.getRemoteAddr());

		ArrayList<Datum> dataList = new ArrayList<>();
		Datum daa = new Datum();

		Root root = new Root();

		ArrayList<OrganizationalProcessOwnerOrganizationalProcess> organizationalProcess_OwnerOrganizationalProces = new ArrayList<>();

		OrganizationalProcessOwnerOrganizationalProcess proce = new OrganizationalProcessOwnerOrganizationalProcess();

		proce.setId("XXXX{][]ss");
		proce.setName("udud907082072eef33'..']p/./../.");

		organizationalProcess_OwnerOrganizationalProces.add(proce);

		daa.setId("Sibhhhhh");
		daa.setName("[Duplicated - CHARVE] Organization processs");
		daa.setOrganizationalProcess_OwnerOrganizationalProcess(organizationalProcess_OwnerOrganizationalProces);

		for (int i = 0; i < 2; i++) {
			dataList.add(daa);
		}

		HopexResponse resp = new HopexResponse();
		resp.setData(dataList);

		Data data = new Data();

		ArrayList<OrganizationalProcess> organizationalProcessList = new ArrayList<>();
		OrganizationalProcess organizationalProcess = new OrganizationalProcess();
		organizationalProcess.setId("Sibhhhhh");
		organizationalProcess.setName("[Duplicated - CHARVE] Organization processs");
		organizationalProcess
				.setOrganizationalProcess_OwnerOrganizationalProcess(organizationalProcess_OwnerOrganizationalProces);

		for (int i = 0; i < 2; i++) {
			organizationalProcessList.add(organizationalProcess);
		}
		
		data.setOrganizationalProcess(organizationalProcessList);
		root.setData(data);

		return root;

	}

}