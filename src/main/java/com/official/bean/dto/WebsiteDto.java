package com.official.bean.dto;

import com.official.bean.Website;

public class WebsiteDto extends Website {
       private Integer todayIPCount;
       private Integer yestIPCount;
       private Integer avgIPCount;
       private Integer monthIPCount;
       private Integer totalIPCount;
       private Integer highIPCount;
       private String highIPDate;
       private Integer todayVisitCount;
       private Integer yestVisitCount;
       private Integer avgVisitCount;
       private Integer monthVisitCount;
       private Integer totalVisitCount;
       private Integer highVisitCount;
       private String highVisitDate;
       private String beginDate;
       private Integer days;
       
	public String getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}
	public Integer getDays() {
		return days;
	}
	public void setDays(Integer days) {
		this.days = days;
	}
	public Integer getTodayIPCount() {
		return todayIPCount;
	}
	public void setTodayIPCount(Integer todayIPCount) {
		this.todayIPCount = todayIPCount;
	}
	public Integer getYestIPCount() {
		return yestIPCount;
	}
	public void setYestIPCount(Integer yestIPCount) {
		this.yestIPCount = yestIPCount;
	}
	public Integer getAvgIPCount() {
		return avgIPCount;
	}
	public void setAvgIPCount(Integer avgIPCount) {
		this.avgIPCount = avgIPCount;
	}
	public Integer getMonthIPCount() {
		return monthIPCount;
	}
	public void setMonthIPCount(Integer monthIPCount) {
		this.monthIPCount = monthIPCount;
	}
	public Integer getTotalIPCount() {
		return totalIPCount;
	}
	public void setTotalIPCount(Integer totalIPCount) {
		this.totalIPCount = totalIPCount;
	}
	public Integer getHighIPCount() {
		return highIPCount;
	}
	public void setHighIPCount(Integer highIPCount) {
		this.highIPCount = highIPCount;
	}
	public String getHighIPDate() {
		return highIPDate;
	}
	public void setHighIPDate(String highIPDate) {
		this.highIPDate = highIPDate;
	}
	public Integer getTodayVisitCount() {
		return todayVisitCount;
	}
	public void setTodayVisitCount(Integer todayVisitCount) {
		this.todayVisitCount = todayVisitCount;
	}
	public Integer getYestVisitCount() {
		return yestVisitCount;
	}
	public void setYestVisitCount(Integer yestVisitCount) {
		this.yestVisitCount = yestVisitCount;
	}
	public Integer getAvgVisitCount() {
		return avgVisitCount;
	}
	public void setAvgVisitCount(Integer avgVisitCount) {
		this.avgVisitCount = avgVisitCount;
	}
	public Integer getMonthVisitCount() {
		return monthVisitCount;
	}
	public void setMonthVisitCount(Integer monthVisitCount) {
		this.monthVisitCount = monthVisitCount;
	}
	public Integer getTotalVisitCount() {
		return totalVisitCount;
	}
	public void setTotalVisitCount(Integer totalVisitCount) {
		this.totalVisitCount = totalVisitCount;
	}
	public Integer getHighVisitCount() {
		return highVisitCount;
	}
	public void setHighVisitCount(Integer highVisitCount) {
		this.highVisitCount = highVisitCount;
	}
	public String getHighVisitDate() {
		return highVisitDate;
	}
	public void setHighVisitDate(String highVisitDate) {
		this.highVisitDate = highVisitDate;
	}
}
