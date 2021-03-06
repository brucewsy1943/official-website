package com.official.dao;

import com.official.bean.dto.CompositionDto;
import com.official.bean.Composition;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface CompositionMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_composition
     *
     * @mbggenerated Sat Oct 12 14:42:14 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_composition
     *
     * @mbggenerated Sat Oct 12 14:42:14 CST 2019
     */
    int insert(Composition record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_composition
     *
     * @mbggenerated Sat Oct 12 14:42:14 CST 2019
     */
    Composition selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_composition
     *
     * @mbggenerated Sat Oct 12 14:42:14 CST 2019
     */
    List<Composition> selectList(CompositionDto compositionDto);

    /**
     * 查询所有作品的总数
     */
    Integer selectCount(Integer columnId);
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_composition
     *
     * @mbggenerated Sat Oct 12 14:42:14 CST 2019
     */
    int updateByPrimaryKey(Composition record);


    Composition selectCompositionByFileName(@Param("fileName")String fileName);

    /**
     * 更新作品
     */
    Integer updateVoteById(@Param("count") Integer count , @Param("voteId") Integer voteId);
}