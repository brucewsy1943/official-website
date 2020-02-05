package com.official.bean;

import com.official.tree.TreeEntitys;

public class Column extends TreeEntitys<Column>{
    /*
     * 主键id
     */
    private Integer id;

    /*
     * 栏目名
     */
    private String columnname;

    /*
     * 父节点id
     */
    private Integer parentId;

    /*
     * 标识码
     */
    private String refno;

    /*
     * 内容类型(0:页面模板,1:链接地址)
     */
    private Integer linktype;

    /*
     * 链接地址
     */
    private String linkurl;

    /*
     * 模板
     */
    private String template;

    /*
     * 栏目预览图
     */
    private String columnpreview;

    /*
     * 是否可以添加内容(0:不可以,1:可以)
     */
    private Integer addcontent;

    /*
     * 是否隐藏(0:否,1:是)
     */
    private Integer ishidden;

    /*
     * 备注
     */
    private String remark;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_column.id
     *
     * @return the value of t_column.id
     *
     * @mbggenerated Thu Dec 20 10:04:15 CST 2018
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_column.id
     *
     * @param id the value for t_column.id
     *
     * @mbggenerated Thu Dec 20 10:04:15 CST 2018
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_column.columnName
     *
     * @return the value of t_column.columnName
     *
     * @mbggenerated Thu Dec 20 10:04:15 CST 2018
     */
    public String getColumnname() {
        return columnname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_column.columnName
     *
     * @param columnname the value for t_column.columnName
     *
     * @mbggenerated Thu Dec 20 10:04:15 CST 2018
     */
    public void setColumnname(String columnname) {
        this.columnname = columnname == null ? null : columnname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_column.parent_id
     *
     * @return the value of t_column.parent_id
     *
     * @mbggenerated Thu Dec 20 10:04:15 CST 2018
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_column.parent_id
     *
     * @param parentId the value for t_column.parent_id
     *
     * @mbggenerated Thu Dec 20 10:04:15 CST 2018
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_column.refNo
     *
     * @return the value of t_column.refNo
     *
     * @mbggenerated Thu Dec 20 10:04:15 CST 2018
     */
    public String getRefno() {
        return refno;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_column.refNo
     *
     * @param refno the value for t_column.refNo
     *
     * @mbggenerated Thu Dec 20 10:04:15 CST 2018
     */
    public void setRefno(String refno) {
        this.refno = refno == null ? null : refno.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_column.linkType
     *
     * @return the value of t_column.linkType
     *
     * @mbggenerated Thu Dec 20 10:04:15 CST 2018
     */
    public Integer getLinktype() {
        return linktype;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_column.linkType
     *
     * @param linktype the value for t_column.linkType
     *
     * @mbggenerated Thu Dec 20 10:04:15 CST 2018
     */
    public void setLinktype(Integer linktype) {
        this.linktype = linktype;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_column.linkUrl
     *
     * @return the value of t_column.linkUrl
     *
     * @mbggenerated Thu Dec 20 10:04:15 CST 2018
     */
    public String getLinkurl() {
        return linkurl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_column.linkUrl
     *
     * @param linkurl the value for t_column.linkUrl
     *
     * @mbggenerated Thu Dec 20 10:04:15 CST 2018
     */
    public void setLinkurl(String linkurl) {
        this.linkurl = linkurl == null ? null : linkurl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_column.template
     *
     * @return the value of t_column.template
     *
     * @mbggenerated Thu Dec 20 10:04:15 CST 2018
     */
    public String getTemplate() {
        return template;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_column.template
     *
     * @param template the value for t_column.template
     *
     * @mbggenerated Thu Dec 20 10:04:15 CST 2018
     */
    public void setTemplate(String template) {
        this.template = template == null ? null : template.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_column.columnPreview
     *
     * @return the value of t_column.columnPreview
     *
     * @mbggenerated Thu Dec 20 10:04:15 CST 2018
     */
    public String getColumnpreview() {
        return columnpreview;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_column.columnPreview
     *
     * @param columnpreview the value for t_column.columnPreview
     *
     * @mbggenerated Thu Dec 20 10:04:15 CST 2018
     */
    public void setColumnpreview(String columnpreview) {
        this.columnpreview = columnpreview == null ? null : columnpreview.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_column.addContent
     *
     * @return the value of t_column.addContent
     *
     * @mbggenerated Thu Dec 20 10:04:15 CST 2018
     */
    public Integer getAddcontent() {
        return addcontent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_column.addContent
     *
     * @param addcontent the value for t_column.addContent
     *
     * @mbggenerated Thu Dec 20 10:04:15 CST 2018
     */
    public void setAddcontent(Integer addcontent) {
        this.addcontent = addcontent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_column.isHidden
     *
     * @return the value of t_column.isHidden
     *
     * @mbggenerated Thu Dec 20 10:04:15 CST 2018
     */
    public Integer getIshidden() {
        return ishidden;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_column.isHidden
     *
     * @param ishidden the value for t_column.isHidden
     *
     * @mbggenerated Thu Dec 20 10:04:15 CST 2018
     */
    public void setIshidden(Integer ishidden) {
        this.ishidden = ishidden;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_column.remark
     *
     * @return the value of t_column.remark
     *
     * @mbggenerated Thu Dec 20 10:04:15 CST 2018
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_column.remark
     *
     * @param remark the value for t_column.remark
     *
     * @mbggenerated Thu Dec 20 10:04:15 CST 2018
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}