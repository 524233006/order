package com.numberone.project.hn.school.service.impl;

import java.util.List;
import com.numberone.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.numberone.project.hn.school.mapper.HnSchoolMapper;
import com.numberone.project.hn.school.domain.HnSchool;
import com.numberone.project.hn.school.service.IHnSchoolService;
import com.numberone.common.utils.text.Convert;

/**
 * 学校Service业务层处理
 * 
 * @author fhx
 * @date 2020-07-21
 */
@Service
public class HnSchoolServiceImpl implements IHnSchoolService 
{
    @Autowired
    private HnSchoolMapper hnSchoolMapper;

    /**
     * 查询学校
     * 
     * @param id 学校ID
     * @return 学校
     */
    @Override
    public HnSchool selectHnSchoolById(Long id)
    {
        return hnSchoolMapper.selectHnSchoolById(id);
    }

    /**
     * 查询学校列表
     * 
     * @param hnSchool 学校
     * @return 学校
     */
    @Override
    public List<HnSchool> selectHnSchoolList(HnSchool hnSchool)
    {
        return hnSchoolMapper.selectHnSchoolList(hnSchool);
    }

    /**
     * 新增学校
     * 
     * @param hnSchool 学校
     * @return 结果
     */
    @Override
    public int insertHnSchool(HnSchool hnSchool)
    {
        hnSchool.setCreateTime(DateUtils.getNowDate());
        return hnSchoolMapper.insertHnSchool(hnSchool);
    }

    /**
     * 修改学校
     * 
     * @param hnSchool 学校
     * @return 结果
     */
    @Override
    public int updateHnSchool(HnSchool hnSchool)
    {
        hnSchool.setUpdateTime(DateUtils.getNowDate());
        return hnSchoolMapper.updateHnSchool(hnSchool);
    }

    /**
     * 删除学校对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteHnSchoolByIds(String ids)
    {
        return hnSchoolMapper.deleteHnSchoolByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除学校信息
     * 
     * @param id 学校ID
     * @return 结果
     */
    @Override
    public int deleteHnSchoolById(Long id)
    {
        return hnSchoolMapper.deleteHnSchoolById(id);
    }
}
