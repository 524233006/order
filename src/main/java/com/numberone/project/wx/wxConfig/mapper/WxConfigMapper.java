package com.numberone.project.wx.wxConfig.mapper;

import java.util.List;
import com.numberone.project.wx.wxConfig.domain.WxConfig;

/**
 * 微信配置Mapper接口
 * 
 * @author fhx
 * @date 2020-07-21
 */
public interface WxConfigMapper 
{
    /**
     * 查询微信配置
     * 
     * @param id 微信配置ID
     * @return 微信配置
     */
    public WxConfig selectWxConfigById(Long id);

    /**
     * 查询微信配置列表
     * 
     * @param wxConfig 微信配置
     * @return 微信配置集合
     */
    public List<WxConfig> selectWxConfigList(WxConfig wxConfig);

    /**
     * 新增微信配置
     * 
     * @param wxConfig 微信配置
     * @return 结果
     */
    public int insertWxConfig(WxConfig wxConfig);

    /**
     * 修改微信配置
     * 
     * @param wxConfig 微信配置
     * @return 结果
     */
    public int updateWxConfig(WxConfig wxConfig);

    /**
     * 删除微信配置
     * 
     * @param id 微信配置ID
     * @return 结果
     */
    public int deleteWxConfigById(Long id);

    /**
     * 批量删除微信配置
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWxConfigByIds(String[] ids);
    
    /**
     * 查询微信配置
     * 
     * @param appId 微信appID
     * @return 微信配置
     */
	public WxConfig selectWxConfigByAppId(String appId);
}
