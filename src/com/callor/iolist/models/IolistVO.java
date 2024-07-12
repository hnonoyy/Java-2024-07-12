package com.callor.iolist.models;

import java.sql.Date;

public class IolistVO {

	public Date ic_day;
	public int ic_division;
	public String ic_name;
	public int ic_amount;
	public int ic_cost;
	
	public int ic_purchaseCost() {
		if(ic_division == 1) {
			return ic_cost * ic_amount; 
		}else {
			return 0;
		}
	}
	

	public int ic_salesCost() {
		if(ic_division == 2) {
			return ic_cost * ic_amount; 
		}else {
			return 0;
		}
	}
	
	
	
}

