package com.qf.dao.impl;

import com.qf.dao.ManagerDao;
import com.qf.entity.Manager;
import com.qf.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ManagerDaoImpl implements ManagerDao {
    //获得连接池对象
    QueryRunner queryRunner = new QueryRunner(DruidUtils.getDruidDataSource());

    //tb_managers表中的名字和密码去找人【1】
    @Override
    public Manager findByNameAndPwd(String loginName, String loginPwd) {
        String sql = "SELECT mgr_id mgrId,login_name loginName,login_pwd loginPwd,mgr_name mgrName," +
                "mgr_gender mgrGender,mgr_tel mgrTel,mgr_email mgrEmail,mgr_qq mgrQQ,create_time createTime\n" +
                "FROM tb_managers\n" +
                "WHERE login_name=? and login_pwd=?";

        Manager manager = null;
        try {
            manager = queryRunner.query(sql, new BeanHandler<>(Manager.class), loginName, loginPwd);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return manager;
    }

    @Override
    public List<Manager> selectManagers() {
        //查询所有管理员信息
        List<Manager> managerList = new ArrayList<>();
        try {
            String sql = "select mgr_id mgrId,login_name loginName,login_pwd loginPwd,mgr_name mgrName," +
                    "mgr_gender mgrGender,mgr_tel mgrTel,mgr_email mgrEmail,mgr_qq mgrQQ,create_time createTime from tb_managers";
            managerList = queryRunner.query(sql, new BeanListHandler<>(Manager.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return managerList;
    }


    @Override
    public int insertManager(Manager manager) {
        //添加管理员
        int i = 0;
        try {
            String sql = "insert into tb_managers(mgr_id,login_name,login_pwd,mgr_name,mgr_gender,mgr_tel,mgr_email,mgr_qq,create_time) values(?,?,?,?,?,?,?,?,?)";
            Object[] params = {manager.getMgrId(),manager.getLoginName(),manager.getLoginPwd(),manager.getMgrName(),manager.getMgrGender(),manager.getMgrTel(),manager.getMgrEmail(),manager.getMgrQQ(),manager.getCreateTime()};
            i = queryRunner.update(sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    @Override
    public int insertMgrAndRole(String mgrId, int roleId) {
        //添加管理员和角色的关系
        int i = 0;
        try {
            String sql = "insert into tb_mgr_role(mgr_id,role_id) values(?,?)";
            i = queryRunner.update(sql, mgrId, roleId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    @Override
    public int deleteMgrAndRole(String mgrId) {
        //删除管理员和角色的关系
        int i = 0;
        try {
            String sql ="delete from tb_mgr_role where mgr_id=?";
            i = queryRunner.update(sql, mgrId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    @Override
    public int deleteManager(String mgrId) {
        //删除管理员
        int i = 0;
        try {
            String sql = "delete from tb_managers where mgr_id=?";
            i = queryRunner.update(sql, mgrId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    @Override
    public Manager selectManagerById(String mgrId) {
        //根据id查询管理员信息
       Manager manager = null;
       try {
           String sql = "select mgr_id mgrId,login_name loginName,login_pwd loginPwd,mgr_name mgrName,mgr_gender mgrGender," +
                   "mgr_tel mgrTel,mgr_email mgrEmail,mgr_qq mgrQQ,create_time createTime from tb_managers where mgr_id=?";
           manager = queryRunner.query(sql, new BeanHandler<>(Manager.class), mgrId);

       }catch (SQLException e) {
           e.printStackTrace();
       }
       return manager;
    }

    @Override
    public int updateManager(Manager manager) {
        //修改管理员信息
        int i = 0;
        try {
            String sql = "update tb_managers set login_name=?,login_pwd=?,mgr_name=?,mgr_gender=?,mgr_tel=?,mgr_email=?,mgr_qq=? " +
                    "where mgr_id=?";
            Object[] params = {manager.getLoginName(),manager.getLoginPwd(),manager.getMgrName(),manager.getMgrGender(),
                    manager.getMgrTel(),manager.getMgrEmail(),manager.getMgrQQ(),manager.getMgrId()};
            i = queryRunner.update(sql,params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }
}
