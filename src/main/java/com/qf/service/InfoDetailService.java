package com.qf.service;

import com.qf.entity.InfoDetail;

import java.util.List;

/**
 * @author Jerforce
 * @date 2023/7/20 星期四 16:10:08
 */
public interface InfoDetailService {
    // 根据评估类目ID，查询此评估类目下的所有的评估选项
    List<InfoDetail> ListInfoDetailsByBasicInfo(int basicInfoId);
    // 添加评估选项
    boolean saveInfoDetail(InfoDetail infoDetail,int basicInfoId);
}
