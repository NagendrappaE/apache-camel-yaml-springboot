package com.ece.camel.yaml.spring.bean;

import java.util.ArrayList;

public class OrganizationalProcess{
    public String id;
    public String name;
    public ArrayList<OrganizationalProcessOwnerOrganizationalProcess> organizationalProcess_OwnerOrganizationalProcess;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<OrganizationalProcessOwnerOrganizationalProcess> getOrganizationalProcess_OwnerOrganizationalProcess() {
		return organizationalProcess_OwnerOrganizationalProcess;
	}
	public void setOrganizationalProcess_OwnerOrganizationalProcess(
			ArrayList<OrganizationalProcessOwnerOrganizationalProcess> organizationalProcess_OwnerOrganizationalProcess) {
		this.organizationalProcess_OwnerOrganizationalProcess = organizationalProcess_OwnerOrganizationalProcess;
	}
}
