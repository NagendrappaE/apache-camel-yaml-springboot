package com.ece.camel.yaml.spring.bean;

import java.util.ArrayList;

public class HopexResponse{
    public ArrayList<Datum> getData() {
		return data;
	}

	public void setData(ArrayList<Datum> data) {
		this.data = data;
	}

	public ArrayList<Datum> data;
}
