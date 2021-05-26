package com.numberone.project.wx.account.mapper;

import java.util.List;
import com.numberone.project.wx.account.domain.WxAccount;

/**
 * 粉丝Mapper接口
 * 
 * @author fhx
 * @date 2020-07-22
 */
public interface WxAccountMapper 
{
    /**
     * 查询粉丝
     * 
     * @param id 粉丝ID
     * @return 粉丝
     */
    public WxAccount selectWxAccountById(Long id);

    /**
     * 查询粉丝列表
     * 
     * @param wxAccount 粉丝
     * @return 粉丝集合
     */
    public List<WxAccount> selectWxAccountList(WxAccount wxAccount);

    /**
     * 新增粉丝
     * 
     * @param wxAccount 粉丝
     * @return 结果
     */
    public int insertWxAccount(WxAccount wxAccount);

    /**
     * 修改粉丝
     * 
     * @param wxAccount 粉丝
     * @return 结果
     */
    public int updateWxAccount(WxAccount wxAccount);

    /**
     * 删除粉丝
     * 
     * @param id 粉丝ID
     * @return 结果
     */
    public int deleteWxAccountById(Long id);

    /**
     * 批量删除粉丝
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWxAccountByIds(String[] ids);

	public WxAccount selectWxAccountByOpenId(String openId);
}
