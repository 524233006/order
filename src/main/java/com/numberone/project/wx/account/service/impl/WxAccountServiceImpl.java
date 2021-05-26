package com.numberone.project.wx.account.service.impl;

import java.util.List;
import com.numberone.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.numberone.project.wx.account.mapper.WxAccountMapper;
import com.numberone.project.wx.account.domain.WxAccount;
import com.numberone.project.wx.account.service.IWxAccountService;
import com.numberone.common.utils.text.Convert;

/**
 * 粉丝Service业务层处理
 * 
 * @author fhx
 * @date 2020-07-22
 */
@Service
public class WxAccountServiceImpl implements IWxAccountService 
{
    @Autowired
    private WxAccountMapper wxAccountMapper;

    /**
     * 查询粉丝
     * 
     * @param id 粉丝ID
     * @return 粉丝
     */
    @Override
    public WxAccount selectWxAccountById(Long id)
    {
        return wxAccountMapper.selectWxAccountById(id);
    }

    /**
     * 查询粉丝列表
     * 
     * @param wxAccount 粉丝
     * @return 粉丝
     */
    @Override
    public List<WxAccount> selectWxAccountList(WxAccount wxAccount)
    {
        return wxAccountMapper.selectWxAccountList(wxAccount);
    }

    /**
     * 新增粉丝
     * 
     * @param wxAccount 粉丝
     * @return 结果
     */
    @Override
    public int insertWxAccount(WxAccount wxAccount)
    {
        wxAccount.setCreateTime(DateUtils.getNowDate());
        return wxAccountMapper.insertWxAccount(wxAccount);
    }

    /**
     * 修改粉丝
     * 
     * @param wxAccount 粉丝
     * @return 结果
     */
    @Override
    public int updateWxAccount(WxAccount wxAccount)
    {
        wxAccount.setUpdateTime(DateUtils.getNowDate());
        return wxAccountMapper.updateWxAccount(wxAccount);
    }

    /**
     * 删除粉丝对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteWxAccountByIds(String ids)
    {
        return wxAccountMapper.deleteWxAccountByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除粉丝信息
     * 
     * @param id 粉丝ID
     * @return 结果
     */
    @Override
    public int deleteWxAccountById(Long id)
    {
        return wxAccountMapper.deleteWxAccountById(id);
    }

	@Override
	public WxAccount selectWxAccountByOpenId(String openId) {
		// TODO Auto-generated method stub
		return wxAccountMapper.selectWxAccountByOpenId(openId);
	}
}
