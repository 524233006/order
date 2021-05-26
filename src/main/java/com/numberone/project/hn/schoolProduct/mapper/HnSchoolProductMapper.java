package com.numberone.project.hn.schoolProduct.mapper;

import java.util.List;
import com.numberone.project.hn.schoolProduct.domain.HnSchoolProduct;

/**
 * 学校产品Mapper接口
 * 
 * @author fhx
 * @date 2020-07-29
 */
public interface HnSchoolProductMapper 
{
    /**
     * 查询学校产品
     * 
     * @param id 学校产品ID
     * @return 学校产品
     */
    public HnSchoolProduct selectHnSchoolProductById(Long id);

    /**
     * 查询学校产品列表
     * 
     * @param hnSchoolProduct 学校产品
     * @return 学校产品集合
     */
    public List<HnSchoolProduct> selectHnSchoolProductList(HnSchoolProduct hnSchoolProduct);

    /**
     * 新增学校产品
     * 
     * @param hnSchoolProduct 学校产品
     * @return 结果
     */
    public int insertHnSchoolProduct(HnSchoolProduct hnSchoolProduct);

    /**
     * 修改学校产品
     * 
     * @param hnSchoolProduct 学校产品
     * @return 结果
     */
    public int updateHnSchoolProduct(HnSchoolProduct hnSchoolProduct);

    /**
     * 删除学校产品
     * 
     * @param id 学校产品ID
     * @return 结果
     */
    public int deleteHnSchoolProductById(Long id);

    /**
     * 批量删除学校产品
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteHnSchoolProductByIds(String[] ids);
}
