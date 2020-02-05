package com.official.bean.dto;

import com.official.bean.Link;

public class LinkDto extends  Link{
          
	   private  String pubtimes;
	   private String expiredtimes;
	public String getPubtimes() {
		return pubtimes;
	}
	public void setPubtimes(String pubtimes) {
		this.pubtimes = pubtimes;
	}
	public String getExpiredtimes() {
		return expiredtimes;
	}
	public void setExpiredtimes(String expiredtimes) {
		this.expiredtimes = expiredtimes;
	}
	
	   
}
