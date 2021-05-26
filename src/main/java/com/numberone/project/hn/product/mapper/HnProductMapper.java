package com.numberone.project.hn.product.mapper;

import java.util.List;
import com.numberone.project.hn.product.domain.HnProduct;

/**
 * 产品Mapper接口
 * 
 * @author fhx
 * @date 2020-07-28
 */
public interface HnProductMapper 
{
    /**
     * 查询产品
     * 
     * @param id 产品ID
     * @return 产品
     */
    public HnProduct selectHnProductById(Long id);

    /**
     * 查询产品列表
     * 
     * @param hnProduct 产品
     * @return 产品集合
     */
    public List<HnProduct> selectHnProductList(HnProduct hnProduct);

    /**
     * 新增产品
     * 
     * @param hnProduct 产品
     * @return 结果
     */
    public int insertHnProduct(HnProduct hnProduct);

    /**
     * 修改产品
     * 
     * @param hnProduct 产品
     * @return 结果
     */
    public int updateHnProduct(HnProduct hnProduct);

    /**
     * 删除产品
     * 
     * @param id 产品ID
     * @return 结果
     */
    public int deleteHnProductById(Long id);

    /**
     * 批量删除产品
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteHnProductByIds(String[] ids);
}
