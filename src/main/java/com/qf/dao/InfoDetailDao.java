package com.qf.dao;

import com.qf.entity.InfoDetail;
import java.util.List;

/**
 * @author Jerforce
 * @date 2023/7/20 星期四 16:03:32
 */
public interface InfoDetailDao {
    // 根据评估类目ID，查询此评估类目下的所有的评估选项
    List<InfoDetail> selectInfoDetailsByBasicInfo(int basicInfoId);
    // 添加评估选项
    int insertInfoDetail(InfoDetail infoDetail,int basicInfoId);


}
