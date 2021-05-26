package com.numberone.project.hn.product.service.impl;

import java.util.List;
import com.numberone.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.numberone.project.hn.product.mapper.HnProductMapper;
import com.numberone.project.hn.product.domain.HnProduct;
import com.numberone.project.hn.product.service.IHnProductService;
import com.numberone.common.utils.text.Convert;

/**
 * 产品Service业务层处理
 * 
 * @author fhx
 * @date 2020-07-28
 */
@Service
public class HnProductServiceImpl implements IHnProductService 
{
    @Autowired
    private HnProductMapper hnProductMapper;

    /**
     * 查询产品
     * 
     * @param id 产品ID
     * @return 产品
     */
    @Override
    public HnProduct selectHnProductById(Long id)
    {
        return hnProductMapper.selectHnProductById(id);
    }

    /**
     * 查询产品列表
     * 
     * @param hnProduct 产品
     * @return 产品
     */
    @Override
    public List<HnProduct> selectHnProductList(HnProduct hnProduct)
    {
        return hnProductMapper.selectHnProductList(hnProduct);
    }

    /**
     * 新增产品
     * 
     * @param hnProduct 产品
     * @return 结果
     */
    @Override
    public int insertHnProduct(HnProduct hnProduct)
    {
        hnProduct.setCreateTime(DateUtils.getNowDate());
        return hnProductMapper.insertHnProduct(hnProduct);
    }

    /**
     * 修改产品
     * 
     * @param hnProduct 产品
     * @return 结果
     */
    @Override
    public int updateHnProduct(HnProduct hnProduct)
    {
        hnProduct.setUpdateTime(DateUtils.getNowDate());
        return hnProductMapper.updateHnProduct(hnProduct);
    }

    /**
     * 删除产品对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteHnProductByIds(String ids)
    {
        return hnProductMapper.deleteHnProductByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除产品信息
     * 
     * @param id 产品ID
     * @return 结果
     */
    @Override
    public int deleteHnProductById(Long id)
    {
        return hnProductMapper.deleteHnProductById(id);
    }
}
