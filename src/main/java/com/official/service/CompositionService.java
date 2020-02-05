package com.official.service;

import com.official.bean.Composition;
import com.official.bean.dto.CompositionDto;

import java.util.List;

public interface CompositionService {
    /**
     * 查询所有要投票的作品
     * @return
     * @param compositionDto
     */
    List<Composition> getCompositionAll(CompositionDto compositionDto);

    /**
     * 查询所有作品的总数
     */

    Integer selectCount(Integer columnId);
    
    
    //插入作品
    public void addCompositionData(Composition composition);


    //更新作品
    public void updateComposition(CompositionDto compositionDto);

    //上传重复作品的时候，需要判断是要插入还是更新
    public boolean isCompositionNeedInserted(String fileName,Integer columnId,Integer employeeId);

    /**
     * app更新作品
     */
    Integer updateVoteById(CompositionDto compositionDto);

}
