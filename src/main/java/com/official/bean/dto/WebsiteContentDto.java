package com.official.bean.dto;

public class WebsiteContentDto {
          private String title;
          private Integer visit;
          private double visitPercent;
          private Integer ipCount;
          private double ipPercent;
          private String columnName;
		public String getColumnName() {
			return columnName;
		}
		public void setColumnName(String columnName) {
			this.columnName = columnName;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public Integer getVisit() {
			return visit;
		}
		public void setVisit(Integer visit) {
			this.visit = visit;
		}
		public double getVisitPercent() {
			return visitPercent;
		}
		public void setVisitPercent(double visitPercent) {
			this.visitPercent = visitPercent;
		}
		public Integer getIpCount() {
			return ipCount;
		}
		public void setIpCount(Integer ipCount) {
			this.ipCount = ipCount;
		}
		public double getIpPercent() {
			return ipPercent;
		}
		public void setIpPercent(double ipPercent) {
			this.ipPercent = ipPercent;
		}
}
