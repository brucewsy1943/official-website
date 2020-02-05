package com.official.bean.dto;
import com.official.bean.Article;

public class ArticleDto extends Article{
	private String columnName;
    private String userName;
    private String columnIds;
    private String pubtimes;
    private String expiredtimes;
    private String createdtime;
	
    public ArticleDto()
    {
    }

    public String getCreatedtime()
    {
        return createdtime;
    }

    public void setCreatedtime(String createdtime)
    {
        this.createdtime = createdtime;
    }

    public String getPubtimes()
    {
        return pubtimes;
    }

    public void setPubtimes(String pubtimes)
    {
        this.pubtimes = pubtimes;
    }

    public String getExpiredtimes()
    {
        return expiredtimes;
    }

    public void setExpiredtimes(String expiredtimes)
    {
        this.expiredtimes = expiredtimes;
    }

    public String getColumnIds()
    {
        return columnIds;
    }

    public void setColumnIds(String columnIds)
    {
        this.columnIds = columnIds;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getColumnName()
    {
        return columnName;
    }

    public void setColumnName(String columnName)
    {
        this.columnName = columnName;
    }

    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("ArticleDto [columnName=").append(columnName).append(", userName=").append(userName).append(", columnIds=").append(columnIds).append(", pubtimes=").append(pubtimes).append(", expiredtimes=").append(expiredtimes).append("]");
        return builder.toString();
    }

    
}
