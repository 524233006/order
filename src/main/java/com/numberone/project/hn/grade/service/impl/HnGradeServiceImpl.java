package com.numberone.project.hn.grade.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.numberone.project.hn.grade.mapper.HnGradeMapper;
import com.numberone.project.hn.grade.domain.HnGrade;
import com.numberone.project.hn.grade.service.IHnGradeService;
import com.numberone.project.hn.school.domain.HnSchool;
import com.numberone.common.utils.text.Convert;

/**
 * 年级Service业务层处理
 * 
 * @author fhx
 * @date 2020-10-10
 */
@Service
public class HnGradeServiceImpl implements IHnGradeService 
{
    @Autowired
    private HnGradeMapper hnGradeMapper;

    /**
     * 查询年级
     * 
     * @param gradeId 年级ID
     * @return 年级
     */
    @Override
    public HnGrade selectHnGradeById(Long gradeId)
    {
        return hnGradeMapper.selectHnGradeById(gradeId);
    }

    /**
     * 查询年级列表
     * 
     * @param hnGrade 年级
     * @return 年级
     */
    @Override
    public List<HnGrade> selectHnGradeList(HnGrade hnGrade)
    {
        return hnGradeMapper.selectHnGradeList(hnGrade);
    }

    /**
     * 新增年级
     * 
     * @param hnGrade 年级
     * @return 结果
     */
    @Override
    public int insertHnGrade(HnGrade hnGrade)
    {
        return hnGradeMapper.insertHnGrade(hnGrade);
    }

    /**
     * 修改年级
     * 
     * @param hnGrade 年级
     * @return 结果
     */
    @Override
    public int updateHnGrade(HnGrade hnGrade)
    {
        return hnGradeMapper.updateHnGrade(hnGrade);
    }

    /**
     * 删除年级对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteHnGradeByIds(String ids)
    {
        return hnGradeMapper.deleteHnGradeByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除年级信息
     * 
     * @param gradeId 年级ID
     * @return 结果
     */
    @Override
    public int deleteHnGradeById(Long gradeId)
    {
        return hnGradeMapper.deleteHnGradeById(gradeId);
    }

	
}
