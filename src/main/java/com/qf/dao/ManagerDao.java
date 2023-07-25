package com.qf.dao;

import com.qf.entity.Manager;

import java.util.List;

public interface ManagerDao {
    //以名字和密码去找有没有这个人【登录页面】
    Manager findByNameAndPwd(String loginName,String loginPwd);

    //查询所有管理员信息
    List<Manager> selectManagers();

    //添加管理员
    int insertManager(Manager manager);

    //保存管理员与角色管理关系
    int insertMgrAndRole(String mgrId,int roleId);

    //根据管理员id删除与角色的关联
    int deleteMgrAndRole(String mgrId);

    //根据管理员id删除管理员
    int deleteManager(String mgrId);

    //根据管理员id查询管理员信息
    Manager selectManagerById(String mgrId);

    //根据管理员id修改管理员信息
    int updateManager(Manager manager);
}
