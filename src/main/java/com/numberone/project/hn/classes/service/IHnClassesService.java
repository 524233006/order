package com.numberone.project.hn.classes.service;

import java.util.List;
import com.numberone.project.hn.classes.domain.HnClasses;
import com.numberone.project.system.user.domain.User;

/**
 * 班级Service接口
 * 
 * @author fhx
 * @date 2020-07-28
 */
public interface IHnClassesService 
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
     * 批量删除班级
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteHnClassesByIds(String ids);

    /**
     * 删除班级信息
     * 
     * @param id 班级ID
     * @return 结果
     */
    public int deleteHnClassesById(Long id);
    
    /**
     * 查询学校和年级查询班级列表
     * 
     * @param id 班级ID
     * @return 班级
     */
    public List<HnClasses> selectHnClassesBySchoolAndGrade(Long schoolId,Long gradeId);
    
    /**
     * 导入班级数据
     * 
     * @param classesList 班级数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @return 结果
     */
    public String importClasses(List<HnClasses> classesList, Boolean isUpdateSupport);

	HnClasses selectHnClassesByClassNameAndSchoolId(Long schoolId,String className);
}
