package com.numberone.project.hn.grade.service;

import java.util.List;
import com.numberone.project.hn.grade.domain.HnGrade;
import com.numberone.project.hn.school.domain.HnSchool;

/**
 * 年级Service接口
 * 
 * @author fhx
 * @date 2020-10-10
 */
public interface IHnGradeService 
{
    /**
     * 查询年级
     * 
     * @param gradeId 年级ID
     * @return 年级
     */
    public HnGrade selectHnGradeById(Long gradeId);

    /**
     * 查询年级列表
     * 
     * @param hnGrade 年级
     * @return 年级集合
     */
    public List<HnGrade> selectHnGradeList(HnGrade hnGrade);

    /**
     * 新增年级
     * 
     * @param hnGrade 年级
     * @return 结果
     */
    public int insertHnGrade(HnGrade hnGrade);

    /**
     * 修改年级
     * 
     * @param hnGrade 年级
     * @return 结果
     */
    public int updateHnGrade(HnGrade hnGrade);

    /**
     * 批量删除年级
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteHnGradeByIds(String ids);

    /**
     * 删除年级信息
     * 
     * @param gradeId 年级ID
     * @return 结果
     */
    public int deleteHnGradeById(Long gradeId);

	
}
