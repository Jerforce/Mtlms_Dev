package com.qf.service;

import com.qf.entity.Brand;

import java.util.List;

public interface BrandService {
    //    -- 根据产品分类查品牌
    List<Brand> findByCId(int cId);

    boolean addBrand(Brand brand,int categoryId);

    Brand getBrandById(int brandId);

    boolean updateBrand(Brand brand);

    boolean deleteBrandById(int brandId);


}
