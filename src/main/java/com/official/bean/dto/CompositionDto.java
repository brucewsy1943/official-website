package com.official.bean.dto;

import com.official.bean.Composition;

public class CompositionDto extends Composition{
    /**
     * 作品栏目
     */
    private Integer columnId;
    /**
     * 首个展示的作品
     */
    private Integer start;

    /**
     * 每页展示数量
     */
    private Integer length;

    /**
     *
     * @return
     */
    private Integer[] voteId;

    /**
     *
     * @return
     */
    private Integer[] voteCount;

    /**
     * 登录用户的Id
     * @return
     */
    private Integer loginId;



    public Integer getColumnId() {
        return columnId;
    }

    public void setColumnId(Integer columnId) {
        this.columnId = columnId;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }


    public Integer[] getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer[] voteCount) {
        this.voteCount = voteCount;
    }

    public Integer[] getVoteId() {
        return voteId;
    }

    public void setVoteId(Integer[] voteId) {
        this.voteId = voteId;
    }

    public Integer getLoginId() {
        return loginId;
    }

    public void setLoginId(Integer loginId) {
        this.loginId = loginId;
    }
}
