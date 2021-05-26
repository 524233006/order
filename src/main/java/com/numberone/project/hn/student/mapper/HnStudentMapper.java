package com.numberone.project.hn.student.mapper;

import java.util.List;
import com.numberone.project.hn.student.domain.HnStudent;

/**
 * 学生Mapper接口
 * 
 * @author fhx
 * @date 2020-07-29
 */
public interface HnStudentMapper 
{
    /**
     * 查询学生
     * 
     * @param id 学生ID
     * @return 学生
     */
    public HnStudent selectHnStudentById(Long id);

    /**
     * 查询学生列表
     * 
     * @param hnStudent 学生
     * @return 学生集合
     */
    public List<HnStudent> selectHnStudentList(HnStudent hnStudent);

    /**
     * 新增学生
     * 
     * @param hnStudent 学生
     * @return 结果
     */
    public int insertHnStudent(HnStudent hnStudent);

    /**
     * 修改学生
     * 
     * @param hnStudent 学生
     * @return 结果
     */
    public int updateHnStudent(HnStudent hnStudent);

    /**
     * 删除学生
     * 
     * @param id 学生ID
     * @return 结果
     */
    public int deleteHnStudentById(Long id);

    /**
     * 批量删除学生
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteHnStudentByIds(String[] ids);

	public List<HnStudent> selectListByOpenId(String openId);
}
