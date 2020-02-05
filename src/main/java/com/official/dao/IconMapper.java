package com.official.dao;

import com.official.bean.Icon;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface IconMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_icon
     *
     * @mbggenerated Wed Jan 09 10:23:45 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_icon
     *
     * @mbggenerated Wed Jan 09 10:23:45 CST 2019
     */
    int insert(Icon record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_icon
     *
     * @mbggenerated Wed Jan 09 10:23:45 CST 2019
     */
    Icon selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_icon
     *
     * @mbggenerated Wed Jan 09 10:23:45 CST 2019
     */
    List<Icon> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_icon
     *
     * @mbggenerated Wed Jan 09 10:23:45 CST 2019
     */
    int updateByPrimaryKey(Icon record);
}