package com.numberone.project.hn.classes.mapper;

import java.util.List;
import com.numberone.project.hn.classes.domain.HnClasses;

/**
 * 班级Mapper接口
 * 
 * @author fhx
 * @date 2020-07-28
 */
public interface HnClassesMapper 
{
    /**
     * 查询班级
     * 
     * @param id 班级ID
     * @return 班级
     */
    public HnClasses selectHnClassesById(Long id);

    /**
     * 查询班级列表
     * 
     * @param hnClasses 班级
     * @return 班级集合
     */
    public List<HnClasses> selectHnClassesList(HnClasses hnClasses);

    /**
     * 新增班级
     * 
     * @param hnClasses 班级
     * @return 结果
     */
    public int insertHnClasses(HnClasses hnClasses);

    /**
     * 修改班级
     * 
     * @param hnClasses 班级
     * @return 结果
     */
    public int updateHnClasses(HnClasses hnClasses);

    /**
     * 删除班级
     * 
     * @param id 班级ID
     * @return 结果
     */
    public int deleteHnClassesById(Long id);

    /**
     * 批量删除班级
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteHnClassesByIds(String[] ids);

	public List<HnClasses> selectHnClassesBySchoolAndGrade(Long schoolId, Long gradeId);

	public HnClasses selectHnClassesByClassNameAndSchoolId(Long schoolId, String className);
}
