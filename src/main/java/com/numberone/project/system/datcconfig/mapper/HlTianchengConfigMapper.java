package com.numberone.project.system.datcconfig.mapper;

import java.util.List;
import com.numberone.project.system.datcconfig.domain.HlTianchengConfig;

/**
 * 月假时间设置Mapper接口
 * 
 * @author fhx
 * @date 2020-06-19
 */
public interface HlTianchengConfigMapper 
{
    /**
     * 查询月假时间设置
     * 
     * @param configId 月假时间设置ID
     * @return 月假时间设置
     */
    public HlTianchengConfig selectHlTianchengConfigById(Long configId);

    /**
     * 查询月假时间设置列表
     * 
     * @param hlTianchengConfig 月假时间设置
     * @return 月假时间设置集合
     */
    public List<HlTianchengConfig> selectHlTianchengConfigList(HlTianchengConfig hlTianchengConfig);

    /**
     * 新增月假时间设置
     * 
     * @param hlTianchengConfig 月假时间设置
     * @return 结果
     */
    public int insertHlTianchengConfig(HlTianchengConfig hlTianchengConfig);

    /**
     * 修改月假时间设置
     * 
     * @param hlTianchengConfig 月假时间设置
     * @return 结果
     */
    public int updateHlTianchengConfig(HlTianchengConfig hlTianchengConfig);

    /**
     * 删除月假时间设置
     * 
     * @param configId 月假时间设置ID
     * @return 结果
     */
    public int deleteHlTianchengConfigById(Long configId);

    /**
     * 批量删除月假时间设置
     * 
     * @param configIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteHlTianchengConfigByIds(String[] configIds);

	public HlTianchengConfig selectOne();
}
