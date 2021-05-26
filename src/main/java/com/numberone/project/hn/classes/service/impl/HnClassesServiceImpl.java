package com.numberone.project.hn.classes.service.impl;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.numberone.common.exception.BusinessException;
import com.numberone.common.utils.DateUtils;
import com.numberone.common.utils.StringUtils;
import com.numberone.common.utils.text.Convert;
import com.numberone.project.hn.classes.domain.HnClasses;
import com.numberone.project.hn.classes.mapper.HnClassesMapper;
import com.numberone.project.hn.classes.service.IHnClassesService;

/**
 * 班级Service业务层处理
 * 
 * @author fhx
 * @date 2020-07-28
 */
@Service
public class HnClassesServiceImpl implements IHnClassesService 
{
    @Autowired
    private HnClassesMapper hnClassesMapper;
    
    private static final Logger log = LoggerFactory.getLogger(HnClassesServiceImpl.class);
   
    /**
     * 查询班级
     * 
     * @param id 班级ID
     * @return 班级
     */
    @Override
    public HnClasses selectHnClassesById(Long id)
    {
        return hnClassesMapper.selectHnClassesById(id);
    }

    /**
     * 查询班级列表
     * 
     * @param hnClasses 班级
     * @return 班级
     */
    @Override
    public List<HnClasses> selectHnClassesList(HnClasses hnClasses)
    {
        return hnClassesMapper.selectHnClassesList(hnClasses);
    }

    /**
     * 新增班级
     * 
     * @param hnClasses 班级
     * @return 结果
     */
    @Override
    public int insertHnClasses(HnClasses hnClasses)
    {
        hnClasses.setCreateTime(DateUtils.getNowDate());
        return hnClassesMapper.insertHnClasses(hnClasses);
    }

    /**
     * 修改班级
     * 
     * @param hnClasses 班级
     * @return 结果
     */
    @Override
    public int updateHnClasses(HnClasses hnClasses)
    {
        hnClasses.setUpdateTime(DateUtils.getNowDate());
        return hnClassesMapper.updateHnClasses(hnClasses);
    }

    /**
     * 删除班级对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteHnClassesByIds(String ids)
    {
        return hnClassesMapper.deleteHnClassesByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除班级信息
     * 
     * @param id 班级ID
     * @return 结果
     */
    @Override
    public int deleteHnClassesById(Long id)
    {
        return hnClassesMapper.deleteHnClassesById(id);
    }

	@Override
	public List<HnClasses> selectHnClassesBySchoolAndGrade(Long schoolId, Long gradeId) {
		// TODO Auto-generated method stub
		return hnClassesMapper.selectHnClassesBySchoolAndGrade(schoolId,gradeId);
	}
	
	@Override
	public HnClasses selectHnClassesByClassNameAndSchoolId(Long schoolId,String className) {
		// TODO Auto-generated method stub
		return hnClassesMapper.selectHnClassesByClassNameAndSchoolId(schoolId,className);
	}
    /**
     * 导入用户数据
     * 
     * @param userList 用户数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @return 结果
     */
    @Override
    public String importClasses(List<HnClasses> classesList, Boolean isUpdateSupport)
    {
        if (StringUtils.isNull(classesList) || classesList.size() == 0)
        {
            throw new BusinessException("导入班级数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        
        for (HnClasses hnClasses : classesList)
        {
            try
            {
            	HnClasses c = hnClassesMapper.selectHnClassesByClassNameAndSchoolId(hnClasses.getSchoolId(),hnClasses.getClassName());
                 if(StringUtils.isNull(c)) {
                	 this.insertHnClasses(hnClasses);
                	 successNum++;
                	 successMsg.append("<br/>" + successNum + "、班级 " + hnClasses.getClassName() + " 导入成功");
                 }
                else
                {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、班级 " + hnClasses.getClassName() + " 已存在");
                }
            }
            catch (Exception e)
            {
                failureNum++;
                String msg = "<br/>" + failureNum + "、班级 " +  hnClasses.getClassName() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
                log.error(msg, e);
            }
        }
        if (failureNum > 0)
        {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new BusinessException(failureMsg.toString());
        }
        else
        {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();
    }
}
