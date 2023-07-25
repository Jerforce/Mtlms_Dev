package com.qf.service.impl;

import com.qf.dao.InfoDetailDao;
import com.qf.dao.impl.InfoDetailDaoImpl;
import com.qf.entity.InfoDetail;
import com.qf.service.InfoDetailService;
import org.apache.commons.dbutils.QueryRunner;

import java.util.List;

/**
 * @author Jerforce
 * @date 2023/7/20 星期四 16:10:40
 */
public class InfoDetailServiceImpl implements InfoDetailService {
   InfoDetailDao infoDetailDao = new InfoDetailDaoImpl();


    @Override
    public List<InfoDetail> ListInfoDetailsByBasicInfo(int basicInfoId) {
        return infoDetailDao.selectInfoDetailsByBasicInfo(basicInfoId);
    }

    @Override
    public boolean saveInfoDetail(InfoDetail infoDetail, int basicInfoId) {
        int i = infoDetailDao.insertInfoDetail(infoDetail, basicInfoId);
        return i>0;
    }
}
