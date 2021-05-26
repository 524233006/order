package com.numberone.project.wx.wxConfig.service.impl;

import java.util.List;
import com.numberone.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.numberone.project.wx.wxConfig.mapper.WxConfigMapper;
import com.numberone.project.wx.wxConfig.domain.WxConfig;
import com.numberone.project.wx.wxConfig.service.IWxConfigService;
import com.numberone.common.utils.text.Convert;

/**
 * 微信配置Service业务层处理
 * 
 * @author fhx
 * @date 2020-07-21
 */
@Service
public class WxConfigServiceImpl implements IWxConfigService 
{
    @Autowired
    private WxConfigMapper wxConfigMapper;

    /**
     * 查询微信配置
     * 
     * @param id 微信配置ID
     * @return 微信配置
     */
    @Override
    public WxConfig selectWxConfigById(Long id)
    {
        return wxConfigMapper.selectWxConfigById(id);
    }

    /**
     * 查询微信配置列表
     * 
     * @param wxConfig 微信配置
     * @return 微信配置
     */
    @Override
    public List<WxConfig> selectWxConfigList(WxConfig wxConfig)
    {
        return wxConfigMapper.selectWxConfigList(wxConfig);
    }

    /**
     * 新增微信配置
     * 
     * @param wxConfig 微信配置
     * @return 结果
     */
    @Override
    public int insertWxConfig(WxConfig wxConfig)
    {
        wxConfig.setCreateTime(DateUtils.getNowDate());
        return wxConfigMapper.insertWxConfig(wxConfig);
    }

    /**
     * 修改微信配置
     * 
     * @param wxConfig 微信配置
     * @return 结果
     */
    @Override
    public int updateWxConfig(WxConfig wxConfig)
    {
        wxConfig.setUpdateTime(DateUtils.getNowDate());
        return wxConfigMapper.updateWxConfig(wxConfig);
    }

    /**
     * 删除微信配置对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteWxConfigByIds(String ids)
    {
        return wxConfigMapper.deleteWxConfigByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除微信配置信息
     * 
     * @param id 微信配置ID
     * @return 结果
     */
    @Override
    public int deleteWxConfigById(Long id)
    {
        return wxConfigMapper.deleteWxConfigById(id);
    }
    /**
     * 查询微信配置
     * 
     * @param appId 微信appID
     * @return 微信配置
     */
	@Override
	public WxConfig selectWxConfigByAppId(String appId) {
		
		return wxConfigMapper.selectWxConfigByAppId(appId);
	}
}
