package com.official.bean;

import com.official.tree.TreeEntity;

public class Permission extends TreeEntity<Permission>{

    private Integer id;

    private String code;

    private String description;
    
    private Integer pId;
    
    private String url;

   private  Integer iconId;
   
   private Integer style;
    
    public Integer getStyle() {
	return style;
}

public void setStyle(Integer style) {
	this.style = style;
}

	public Integer getIconId() {
	return iconId;
}

public void setIconId(Integer iconId) {
	this.iconId = iconId;
}

	private String navCode;
    
    private Integer orderId;
    
    
    public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getpId() {
		return pId;
	}

	public void setpId(Integer pId) {
		this.pId = pId;
	}

	

	public String getNavCode() {
		return navCode;
	}

	public void setNavCode(String navCode) {
		this.navCode = navCode;
	}

	public Integer getPId() {
		return pId;
	}

	public void setPId(Integer pId) {
		this.pId = pId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}