package com.numberone.project.hn.student.service.impl;

import java.util.List;
import com.numberone.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.numberone.project.hn.student.mapper.HnStudentMapper;
import com.numberone.project.hn.student.domain.HnStudent;
import com.numberone.project.hn.student.service.IHnStudentService;
import com.numberone.common.utils.text.Convert;

/**
 * 学生Service业务层处理
 * 
 * @author fhx
 * @date 2020-07-29
 */
@Service
public class HnStudentServiceImpl implements IHnStudentService 
{
    @Autowired
    private HnStudentMapper hnStudentMapper;

    /**
     * 查询学生
     * 
     * @param id 学生ID
     * @return 学生
     */
    @Override
    public HnStudent selectHnStudentById(Long id)
    {
        return hnStudentMapper.selectHnStudentById(id);
    }

    /**
     * 查询学生列表
     * 
     * @param hnStudent 学生
     * @return 学生
     */
    @Override
    public List<HnStudent> selectHnStudentList(HnStudent hnStudent)
    {
        return hnStudentMapper.selectHnStudentList(hnStudent);
    }

    /**
     * 新增学生
     * 
     * @param hnStudent 学生
     * @return 结果
     */
    @Override
    public int insertHnStudent(HnStudent hnStudent)
    {
        hnStudent.setCreateTime(DateUtils.getNowDate());
        return hnStudentMapper.insertHnStudent(hnStudent);
    }

    /**
     * 修改学生
     * 
     * @param hnStudent 学生
     * @return 结果
     */
    @Override
    public int updateHnStudent(HnStudent hnStudent)
    {
        hnStudent.setUpdateTime(DateUtils.getNowDate());
        return hnStudentMapper.updateHnStudent(hnStudent);
    }

    /**
     * 删除学生对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteHnStudentByIds(String ids)
    {
        return hnStudentMapper.deleteHnStudentByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除学生信息
     * 
     * @param id 学生ID
     * @return 结果
     */
    @Override
    public int deleteHnStudentById(Long id)
    {
        return hnStudentMapper.deleteHnStudentById(id);
    }

	@Override
	public List<HnStudent> selectListByOpenId(String openId) {
		// TODO Auto-generated method stub
		return hnStudentMapper.selectListByOpenId(openId);
	}
}
