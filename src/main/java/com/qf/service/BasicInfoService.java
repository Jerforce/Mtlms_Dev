package com.qf.service;

import com.qf.entity.BasicInfo;

import java.util.List;

public interface BasicInfoService {
    //查询全部的评估类目
    //-- 查询评估类目【评估类目表】
    List<BasicInfo> findAll();

    //添加基本评估类目
    boolean saveBasicInfo(BasicInfo basicInfo);

    boolean deleteBasicInfoById(int bId);

    boolean updateBasicInfo(BasicInfo basicInfo);





}
