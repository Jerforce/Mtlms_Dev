package com.qf.dao;

import com.qf.entity.BasicInfo;
import com.qf.entity.InfoDetail;

import java.util.List;

public interface BasicInfoDao {
    //查询全部的基本评估类目
    //-- 查询评估类目【评估类目表】
    //{basic_info_name IN ('版本', '保修期', '型号', '机身内存', '颜色', '开机情况', '功能情况（可多选或不选）', '外观情况', '触摸屏情况', '显示屏情况', '维修情况', '配件', '拆修情况')}
    List<BasicInfo> findAll();
    //根据评估类目的id查询评估选项
    //-- 根据评估项目的id去查询对应的评估选项[评估选项列表]
    List<InfoDetail> findByBId(int bId);

    //添加基本评估类目
    int insertBasicInfo(BasicInfo basicInfo);

    int deleteBasicInfoById(int bId);

    int updateBasicInfo(BasicInfo basicInfo);



}
