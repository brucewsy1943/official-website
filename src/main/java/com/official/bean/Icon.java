package com.official.bean;

public class Icon {

    private Integer id;

 
    private String code;

    
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
}