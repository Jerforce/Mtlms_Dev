package com.qf.dao;

import com.qf.entity.Brand;

import java.util.List;

public interface BrandDao {
    //    -- 根据产品分类查品牌
    List<Brand> findByCId(int cId);

    int insertBrand(Brand brand);

    int insertCategoryAndBrand(int cid,int bid);

    Brand selectBrandByBrandId(int brandId);

    int updateBrand(Brand brand);

    int deleteCategoryAndBrand(int brandId);

    int deleteBrand(int brandId);


}
