package com.qf.dao.impl;

import com.qf.dao.InfoDetailDao;
import com.qf.entity.InfoDetail;
import com.qf.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jerforce
 * @date 2023/7/20 星期四 16:06:05
 */
public class InfoDetailDaoImpl implements InfoDetailDao {
    QueryRunner queryRunner = new QueryRunner(DruidUtils.getDruidDataSource());

    @Override
    // 根据评估类目ID，查询此评估类目下的所有的评估选项
    public List<InfoDetail> selectInfoDetailsByBasicInfo(int basicInfoId) {
        List<InfoDetail> infoDetailList = new ArrayList<>();
        try {
            String sql = "select info_detail_id infoDetailId,info_detail_name infoDetailName,info_detail_desc infoDetailDesc from tb_info_detail where fk_basic_info_id=?";

            infoDetailList = queryRunner.query(sql, new BeanListHandler<InfoDetail>(InfoDetail.class), basicInfoId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return infoDetailList;
    }

    @Override
    // 添加评估选项
    public int insertInfoDetail(InfoDetail infoDetail, int basicInfoId) {
        int i = 0;
        try {
            String sql = "insert into tb_info_detail(info_detail_name,info_detail_desc,fk_basic_info_id) values(?,?,?)";
            i = queryRunner.update(sql, infoDetail.getInfoDetailName(), infoDetail.getInfoDetailDesc(), basicInfoId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }
}
