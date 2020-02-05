package com.official.bean;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Article {
    /*
     * 主键id
     */
    private Integer id;

    /*
     * 标题
     */
    private String title;

    /*
     * 栏目id(外键)
     */
    private Integer columnid;

    /*
     * 是否置顶(0:不置顶,1:置顶)
     */
    private Integer isstick;

    /*
     * 输入的内容类型(0:输入内容,1:输入链接)
     */
    private Integer linktype;

    /*
     * 输入的链接地址
     */
    private String linkurl;

    /*
     * 摘要
     */
    private String interception;

    /*
     * 内容来源
     */
    private String contentsource;

    /*
     * 状态(1.审核通过;2.暂存;3.审核未通过;4.未审核)
     */
    private Integer status;

    /*
     * 点击数
     */
    private Integer clicks;

    /*
     * 发布时间类型(0:立即发布,1:自定义时间)
     */
    private Integer pubtimetype;

    /*
     * 发布时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp pubtime;

    /*
     * 过期时间类型(0:永不过期,1:自定义过期时间)
     */
    private Integer expiredtimetype;

    /*
     * 过期时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp expiredtime;

    /*
     * 导航图片路径
     */
    private String navpicturepath;

    /*
     * 附件路径
     */
    private String attachmentpath;

    /*
     * 优先级
     */
    private Integer orderpriority;

    /*
     * 文章内容
     */
    private String content;

    /*
     * 新增时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp createdTime;
    
    /*
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp modifiedTime;
    
    /*
     * 用户id
     */
    private Integer userId;
    
    @Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Article [id=").append(id).append(", title=").append(title).append(", columnid=")
				.append(columnid).append(", isstick=").append(isstick).append(", linktype=").append(linktype)
				.append(", linkurl=").append(linkurl).append(", interception=").append(interception)
				.append(", contentsource=").append(contentsource).append(", status=").append(status).append(", clicks=")
				.append(clicks).append(", pubtimetype=").append(pubtimetype).append(", pubtime=").append(pubtime)
				.append(", expiredtimetype=").append(expiredtimetype).append(", expiredtime=").append(expiredtime)
				.append(", navpicturepath=").append(navpicturepath).append(", attachmentpath=").append(attachmentpath)
				.append(", orderpriority=").append(orderpriority).append(", content=").append(content)
				.append(", createdTime=").append(createdTime).append(", modifiedTime=").append(modifiedTime)
				.append(", userId=").append(userId).append("]");
		return builder.toString();
	}


	public Integer getUserId() {
		return userId;
	}


	public void setUserId(Integer userId) {
		this.userId = userId;
	}


	public Timestamp getCreatedTime() {
		return createdTime;
	}


	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}


	public Timestamp getModifiedTime() {
		return modifiedTime;
	}


	public void setModifiedTime(Timestamp modifiedTime) {
		this.modifiedTime = modifiedTime;
	}


	public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getColumnid() {
        return columnid;
    }


    public void setColumnid(Integer columnid) {
        this.columnid = columnid;
    }


    public Integer getIsstick() {
        return isstick;
    }


    public void setIsstick(Integer isstick) {
        this.isstick = isstick;
    }


    public Integer getLinktype() {
        return linktype;
    }


    public void setLinktype(Integer linktype) {
        this.linktype = linktype;
    }


    public String getLinkurl() {
        return linkurl;
    }


    public void setLinkurl(String linkurl) {
        this.linkurl = linkurl == null ? null : linkurl.trim();
    }

    public String getInterception() {
        return interception;
    }


    public void setInterception(String interception) {
        this.interception = interception == null ? null : interception.trim();
    }


    public String getContentsource() {
        return contentsource;
    }


    public void setContentsource(String contentsource) {
        this.contentsource = contentsource == null ? null : contentsource.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


    public Integer getClicks() {
        return clicks;
    }

 
    public void setClicks(Integer clicks) {
        this.clicks = clicks;
    }


    public Integer getPubtimetype() {
        return pubtimetype;
    }


    public void setPubtimetype(Integer pubtimetype) {
        this.pubtimetype = pubtimetype;
    }

    public Timestamp getPubtime() {
        return pubtime;
    }


    public void setPubtime(Timestamp pubtime) {
        this.pubtime = pubtime;
    }

    
    public Integer getExpiredtimetype() {
        return expiredtimetype;
    }


    public void setExpiredtimetype(Integer expiredtimetype) {
        this.expiredtimetype = expiredtimetype;
    }


    public Timestamp getExpiredtime() {
        return expiredtime;
    }

    
    public void setExpiredtime(Timestamp expiredtime) {
        this.expiredtime = expiredtime;
    }

  
    public String getNavpicturepath() {
        return navpicturepath;
    }

    
    public void setNavpicturepath(String navpicturepath) {
        this.navpicturepath = navpicturepath == null ? null : navpicturepath.trim();
    }

  
    public String getAttachmentpath() {
        return attachmentpath;
    }

    
    public void setAttachmentpath(String attachmentpath) {
        this.attachmentpath = attachmentpath == null ? null : attachmentpath.trim();
    }

  
    public Integer getOrderpriority() {
        return orderpriority;
    }


    public void setOrderpriority(Integer orderpriority) {
        this.orderpriority = orderpriority;
    }

   
    public String getContent() {
        return content;
    }

    
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}