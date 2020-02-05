package com.official.service.impl;

import com.official.bean.Composition;
import com.official.bean.VoteRecord;
import com.official.bean.dto.CompositionDto;
import com.official.dao.CompositionMapper;
import com.official.dao.VoteRecordMapper;
import com.official.service.CompositionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 作品服务类
 */
@Service("compositionService")
public class CompositionServiceImpl implements CompositionService {
    @Autowired
    private CompositionMapper compositionMapper;

    @Autowired
    private VoteRecordMapper voteRecordMapper;

    /**
     * 查询所有作品的总数
     * @param compositionDto
     * @return
     */
    @Override
    public List<Composition> getCompositionAll(CompositionDto compositionDto) {
        List<Composition> compositionList = compositionMapper.selectList(compositionDto);
        return compositionList;
    }

    /**
     * 查询所有作品的总数
     * @return
     */
    @Override
    public Integer selectCount(Integer columnId) {
        Integer countAll = compositionMapper.selectCount(columnId);
        return countAll;
    }

	@Override
	public void addCompositionData(Composition composition) {
		// TODO Auto-generated method stub
		compositionMapper.insert(composition);
	}


    @Override
    public void updateComposition(CompositionDto compositionDto) {
        Composition composition = compositionMapper.selectByPrimaryKey(compositionDto.getId());
        CompositionDto compositionDto1 = new CompositionDto();
        BeanUtils.copyProperties(composition,compositionDto1);
        compositionMapper.updateByPrimaryKey(compositionDto1);
    }


    @Override
    public boolean isCompositionNeedInserted(String fileName,Integer columnId,Integer employeeId) {

        Composition composition = compositionMapper.selectCompositionByFileName(fileName);

        //找不到，肯定要新增
        if (composition == null){
            return true;
        }

        //栏目不同，新增
        if (!columnId.equals(composition.getColumnid())){
            //composition.setColumnid(columnId);
            //更新
            //compositionMapper.updateByPrimaryKey(composition);
            return true;
        }

        //假如原来没关联上员工信息，现在有了
        if (composition.getEmployeeid() == null && employeeId!=null){
            composition.setEmployeeid(employeeId);
            //更新
            compositionMapper.updateByPrimaryKey(composition);
        }

        //同一个作品，同一个栏目 不添加了
        if (composition != null && columnId.equals(composition.getColumnid())){
            return false;
        }

        return true;
    }

    /**
     * app更新作品
     * @param compositionDto
     */
    @Override
    public Integer updateVoteById(CompositionDto compositionDto) {
        Integer counts = 0 ;
        for (int i = 0; i<compositionDto.getVoteId().length;i++){
            for (int j = 0;j < compositionDto.getVoteCount().length;j++){
                VoteRecord voteRecord = new VoteRecord();
                Integer count = compositionDto.getVoteCount()[i];
                Integer voteId = compositionDto.getVoteId()[j];
                Composition composition = compositionMapper.selectByPrimaryKey(voteId);
                counts = compositionMapper.updateVoteById(count,voteId);
                voteRecord.setEmployeeid(compositionDto.getLoginId());
                voteRecord.setCompositionid(composition.getId());
                voteRecordMapper.insert(voteRecord);
                count = null;
                voteId = null;
                i++;
            }
        }
        return counts;
    }

}
