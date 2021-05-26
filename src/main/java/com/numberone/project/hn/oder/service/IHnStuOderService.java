package com.numberone.project.hn.oder.service;

import java.util.List;
import com.numberone.project.hn.oder.domain.HnStuOder;

/**
 * 订单Service接口
 * 
 * @author fhx
 * @date 2020-07-29
 */
public interface IHnStuOderService 
{
    /**
     * 查询订单
     * 
     * @param id 订单ID
     * @return 订单
     */
    public HnStuOder selectHnStuOderById(Long id);
    
    public HnStuOder selectHnStuOderByOrderCode(String orderCode);
    /**
     * 查询订单列表
     * 
     * @param hnStuOder 订单
     * @return 订单集合
     */
    public List<HnStuOder> selectHnStuOderList(HnStuOder hnStuOder);

    /**
     * 新增订单
     * 
     * @param hnStuOder 订单
     * @return 结果
     */
    public int insertHnStuOder(HnStuOder hnStuOder);

    /**
     * 修改订单
     * 
     * @param hnStuOder 订单
     * @return 结果
     */
    public int updateHnStuOder(HnStuOder hnStuOder);
    /**
     * 修改订单不修改时间
     * 
     * @param hnStuOder 订单
     * @return 结果
     */
    public int updateHnStuOderNoTime(HnStuOder hnStuOder);
    /**
     * 批量删除订单
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteHnStuOderByIds(String ids);

    /**
     * 删除订单信息
     * 
     * @param id 订单ID
     * @return 结果
     */
    public int deleteHnStuOderById(Long id);

	public HnStuOder selectHnStudentId(Long id);
	

	public List<HnStuOder> countByDay(HnStuOder hnStuOder);

	public List<HnStuOder> countByMonth(HnStuOder hnStuOder);

	HnStuOder countByHnStuOder(HnStuOder hnStuOder);

	public HnStuOder countByToday();

	
}
