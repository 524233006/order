package com.numberone.project.hn.school.mapper;

import java.util.List;
import com.numberone.project.hn.school.domain.HnSchool;

/**
 * 学校Mapper接口
 * 
 * @author fhx
 * @date 2020-07-21
 */
public interface HnSchoolMapper 
{
    /**
     * 查询学校
     * 
     * @param id 学校ID
     * @return 学校
     */
    public HnSchool selectHnSchoolById(Long id);

    /**
     * 查询学校列表
     * 
     * @param hnSchool 学校
     * @return 学校集合
     */
    public List<HnSchool> selectHnSchoolList(HnSchool hnSchool);

    /**
     * 新增学校
     * 
     * @param hnSchool 学校
     * @return 结果
     */
    public int insertHnSchool(HnSchool hnSchool);

    /**
     * 修改学校
     * 
     * @param hnSchool 学校
     * @return 结果
     */
    public int updateHnSchool(HnSchool hnSchool);

    /**
     * 删除学校
     * 
     * @param id 学校ID
     * @return 结果
     */
    public int deleteHnSchoolById(Long id);

    /**
     * 批量删除学校
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteHnSchoolByIds(String[] ids);
}
