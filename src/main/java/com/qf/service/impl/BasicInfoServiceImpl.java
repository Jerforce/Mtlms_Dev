package com.qf.service.impl;

import com.qf.dao.BasicInfoDao;
import com.qf.dao.impl.BasicInfoDaoImpl;
import com.qf.entity.BasicInfo;
import com.qf.entity.InfoDetail;
import com.qf.service.BasicInfoService;

import java.util.List;

public class BasicInfoServiceImpl implements BasicInfoService {

    BasicInfoDao dao = new BasicInfoDaoImpl();

    //全部的评估项目和评估选项
    @Override
    public List<BasicInfo> findAll() {
        List<BasicInfo> basicInfoList = dao.findAll();
        for (BasicInfo basicInfo : basicInfoList) {
            List<InfoDetail> detailList = dao.findByBId(basicInfo.getBasicInfoId());
            basicInfo.setInfoDetailList(detailList);
        }
        return basicInfoList;
    }

    @Override
    public boolean saveBasicInfo(BasicInfo basicInfo) {
        //1.添加基本评估类目
        int i = dao.insertBasicInfo(basicInfo);
        return i>0;
    }

    @Override
    public boolean deleteBasicInfoById(int bId) {
        int i = dao.deleteBasicInfoById(bId);
        return i>0;
    }

    @Override
    public boolean updateBasicInfo(BasicInfo basicInfo) {
       int i = dao.updateBasicInfo(basicInfo);
         return i>0;
    }


}
