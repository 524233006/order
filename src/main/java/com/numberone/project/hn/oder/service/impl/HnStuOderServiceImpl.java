package com.numberone.project.hn.oder.service.impl;

import java.util.List;
import com.numberone.common.utils.DateUtils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.numberone.project.hn.oder.mapper.HnStuOderMapper;
import com.numberone.project.hn.oder.domain.HnStuOder;
import com.numberone.project.hn.oder.service.IHnStuOderService;
import com.numberone.common.utils.text.Convert;

/**
 * 订单Service业务层处理
 * 
 * @author fhx
 * @date 2020-07-29
 */
@Service
public class HnStuOderServiceImpl implements IHnStuOderService 
{
    @Autowired
    private HnStuOderMapper hnStuOderMapper;

    /**
     * 查询订单
     * 
     * @param id 订单ID
     * @return 订单
     */
    @Override
    public HnStuOder selectHnStuOderById(Long id)
    {
        return hnStuOderMapper.selectHnStuOderById(id);
    }

    /**
     * 查询订单列表
     * 
     * @param hnStuOder 订单
     * @return 订单
     */
    @Override
    public List<HnStuOder> selectHnStuOderList(HnStuOder hnStuOder)
    {
    	return  hnStuOderMapper.selectHnStuOderList(hnStuOder);
    	
         
    }

    /**
     * 新增订单
     * 
     * @param hnStuOder 订单
     * @return 结果
     */
    @Override
    public int insertHnStuOder(HnStuOder hnStuOder)
    {
        hnStuOder.setCreateTime(DateUtils.getNowDate());
        return hnStuOderMapper.insertHnStuOder(hnStuOder);
    }

    /**
     * 修改订单
     * 
     * @param hnStuOder 订单
     * @return 结果
     */
    @Override
    public int updateHnStuOder(HnStuOder hnStuOder)
    {
    	hnStuOder.setUpdateTime(DateUtils.getNowDate());
       
        return hnStuOderMapper.updateHnStuOder(hnStuOder);
    }

    /**
     * 删除订单对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteHnStuOderByIds(String ids)
    {
        return hnStuOderMapper.deleteHnStuOderByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除订单信息
     * 
     * @param id 订单ID
     * @return 结果
     */
    @Override
    public int deleteHnStuOderById(Long id)
    {
        return hnStuOderMapper.deleteHnStuOderById(id);
    }

	@Override
	public HnStuOder selectHnStuOderByOrderCode(String orderCode) {
		
		return hnStuOderMapper.selectHnStuOderByOrderCode(orderCode);
	}

	@Override
	public HnStuOder selectHnStudentId(Long id) {
		// TODO Auto-generated method stub
		return hnStuOderMapper.selectHnStudentId(id);
	}



	@Override
	public HnStuOder countByHnStuOder(HnStuOder hnStuOder) {
		// TODO Auto-generated method stub
		return hnStuOderMapper.countByHnStuOder(hnStuOder);
	}
	public List<HnStuOder> countByMonth(HnStuOder hnStuOder) {
		// TODO Auto-generated method stub
		return hnStuOderMapper.countByMonth(hnStuOder);
	}
	public List<HnStuOder> countByDay(HnStuOder hnStuOder) {
		// TODO Auto-generated method stub
		return hnStuOderMapper.countByDay(hnStuOder);
	}

	@Override
	public HnStuOder countByToday() {
		// TODO Auto-generated method stub
		return  hnStuOderMapper.countByToday();
	}

	@Override
	public int updateHnStuOderNoTime(HnStuOder hnStuOder) {
		if(hnStuOder.getRefundTime()==null&StringUtils.isNoneEmpty(hnStuOder.getState())) {
			hnStuOder.setRefundTime(DateUtils.getNowDate());
    	}
        return hnStuOderMapper.updateHnStuOder(hnStuOder);
	}
}
