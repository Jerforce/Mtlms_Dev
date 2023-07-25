package com.qf.dao.impl;

import com.qf.dao.BasicInfoDao;
import com.qf.entity.BasicInfo;
import com.qf.entity.InfoDetail;
import com.qf.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class BasicInfoDaoImpl implements BasicInfoDao {

    QueryRunner runner=new QueryRunner(DruidUtils.getDruidDataSource());

    //查询全部的评估类目
    //-- 查询评估类目【评估类目表】
    @Override
    public List<BasicInfo> findAll() {
        String sql="select basic_info_id basicInfoId,\n" +
                "basic_info_name basicInfoName,\n" +
                "basic_info_status basicInfoStatus\n" +
                "from tb_basic_info bi ";

        List<BasicInfo> query = null;
        try {
            query = runner.query(sql, new BeanListHandler<>(BasicInfo.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return query;
    }


    //根据评估类目的id查询评估选项
    //-- 根据评估项目的id去查询对应的评估选项[评估选项列表]
    @Override
    public List<InfoDetail> findByBId(int bId) {
        String sql="select info_detail_id infoDetailId,\n" +
                "info_detail_name infoDetailName,\n" +
                "info_detail_desc infoDetailDesc,\n" +
                "fk_basic_info_id fkBasicInfoId\n" +
                "from tb_info_detail\n" +
                "where fk_basic_info_id=?;";
        List<InfoDetail> query = null;
        try {
            query = runner.query(sql, new BeanListHandler<>(InfoDetail.class), bId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return query;
    }

    @Override
    public int insertBasicInfo(BasicInfo basicInfo) {
        int i = 0;
        try {
            String sql = "insert into tb_basic_info(basic_info_name,basic_info_status) values(?,1)";
               i = runner.update(sql,basicInfo.getBasicInfoName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    @Override
    public int deleteBasicInfoById(int bId) {
        int i = 0;
        try {
            String sql = "delete from tb_basic_info where basic_info_id=?";
            i = runner.update(sql,bId);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return i;
    }

    @Override
    public int updateBasicInfo(BasicInfo basicInfo) {
      int i=0;
        try {
            String sql = "update tb_basic_info set basic_info_name=?,basic_info_status=? where basic_info_id=?";
          Object[] params ={basicInfo.getBasicInfoName(),basicInfo.getBasicInfoStatus(),basicInfo.getBasicInfoId()};
            i = runner.update(sql,params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;

    }
}
