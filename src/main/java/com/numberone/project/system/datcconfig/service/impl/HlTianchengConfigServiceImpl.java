package com.numberone.project.system.datcconfig.service.impl;

import java.util.List;
import com.numberone.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.numberone.project.system.datcconfig.mapper.HlTianchengConfigMapper;
import com.numberone.project.system.datcconfig.domain.HlTianchengConfig;
import com.numberone.project.system.datcconfig.service.IHlTianchengConfigService;
import com.numberone.common.utils.text.Convert;

/**
 * 月假时间设置Service业务层处理
 * 
 * @author fhx
 * @date 2020-06-19
 */
@Service
public class HlTianchengConfigServiceImpl implements IHlTianchengConfigService 
{
    @Autowired
    private HlTianchengConfigMapper hlTianchengConfigMapper;

    /**
     * 查询月假时间设置
     * 
     * @param configId 月假时间设置ID
     * @return 月假时间设置
     */
    @Override
    public HlTianchengConfig selectHlTianchengConfigById(Long configId)
    {
        return hlTianchengConfigMapper.selectHlTianchengConfigById(configId);
    }

    /**
     * 查询月假时间设置列表
     * 
     * @param hlTianchengConfig 月假时间设置
     * @return 月假时间设置
     */
    @Override
    public List<HlTianchengConfig> selectHlTianchengConfigList(HlTianchengConfig hlTianchengConfig)
    {
        return hlTianchengConfigMapper.selectHlTianchengConfigList(hlTianchengConfig);
    }

    /**
     * 新增月假时间设置
     * 
     * @param hlTianchengConfig 月假时间设置
     * @return 结果
     */
    @Override
    public int insertHlTianchengConfig(HlTianchengConfig hlTianchengConfig)
    {
        hlTianchengConfig.setCreateTime(DateUtils.getNowDate());
        return hlTianchengConfigMapper.insertHlTianchengConfig(hlTianchengConfig);
    }

    /**
     * 修改月假时间设置
     * 
     * @param hlTianchengConfig 月假时间设置
     * @return 结果
     */
    @Override
    public int updateHlTianchengConfig(HlTianchengConfig hlTianchengConfig)
    {
        return hlTianchengConfigMapper.updateHlTianchengConfig(hlTianchengConfig);
    }

    /**
     * 删除月假时间设置对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteHlTianchengConfigByIds(String ids)
    {
        return hlTianchengConfigMapper.deleteHlTianchengConfigByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除月假时间设置信息
     * 
     * @param configId 月假时间设置ID
     * @return 结果
     */
    @Override
    public int deleteHlTianchengConfigById(Long configId)
    {
        return hlTianchengConfigMapper.deleteHlTianchengConfigById(configId);
    }

	@Override
	public HlTianchengConfig selectOne() {
		// TODO Auto-generated method stub
		return hlTianchengConfigMapper.selectOne();
	}
}
