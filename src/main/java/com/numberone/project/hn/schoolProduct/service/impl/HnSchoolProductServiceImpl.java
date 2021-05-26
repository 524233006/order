package com.numberone.project.hn.schoolProduct.service.impl;

import java.util.List;
import com.numberone.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.numberone.project.hn.schoolProduct.mapper.HnSchoolProductMapper;
import com.numberone.project.hn.schoolProduct.domain.HnSchoolProduct;
import com.numberone.project.hn.schoolProduct.service.IHnSchoolProductService;
import com.numberone.common.utils.text.Convert;

/**
 * 学校产品Service业务层处理
 * 
 * @author fhx
 * @date 2020-07-29
 */
@Service
public class HnSchoolProductServiceImpl implements IHnSchoolProductService 
{
    @Autowired
    private HnSchoolProductMapper hnSchoolProductMapper;

    /**
     * 查询学校产品
     * 
     * @param id 学校产品ID
     * @return 学校产品
     */
    @Override
    public HnSchoolProduct selectHnSchoolProductById(Long id)
    {
        return hnSchoolProductMapper.selectHnSchoolProductById(id);
    }

    /**
     * 查询学校产品列表
     * 
     * @param hnSchoolProduct 学校产品
     * @return 学校产品
     */
    @Override
    public List<HnSchoolProduct> selectHnSchoolProductList(HnSchoolProduct hnSchoolProduct)
    {
        return hnSchoolProductMapper.selectHnSchoolProductList(hnSchoolProduct);
    }

    /**
     * 新增学校产品
     * 
     * @param hnSchoolProduct 学校产品
     * @return 结果
     */
    @Override
    public int insertHnSchoolProduct(HnSchoolProduct hnSchoolProduct)
    {
        hnSchoolProduct.setCreateTime(DateUtils.getNowDate());
        return hnSchoolProductMapper.insertHnSchoolProduct(hnSchoolProduct);
    }

    /**
     * 修改学校产品
     * 
     * @param hnSchoolProduct 学校产品
     * @return 结果
     */
    @Override
    public int updateHnSchoolProduct(HnSchoolProduct hnSchoolProduct)
    {
        hnSchoolProduct.setUpdateTime(DateUtils.getNowDate());
        return hnSchoolProductMapper.updateHnSchoolProduct(hnSchoolProduct);
    }

    /**
     * 删除学校产品对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteHnSchoolProductByIds(String ids)
    {
        return hnSchoolProductMapper.deleteHnSchoolProductByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除学校产品信息
     * 
     * @param id 学校产品ID
     * @return 结果
     */
    @Override
    public int deleteHnSchoolProductById(Long id)
    {
        return hnSchoolProductMapper.deleteHnSchoolProductById(id);
    }
}
