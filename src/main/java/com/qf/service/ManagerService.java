package com.qf.service;

import com.qf.entity.Manager;

import java.util.List;

public interface ManagerService {
    //以名字和密码去找有没有这个人【登录页面】
    Manager login(String loginName, String loginPwd);

    //查询所有管理员信息
    List<Manager> listManagers();

    //保存管理员
    boolean saveManager(Manager manager, int roldId);

    //根据id删除管理员
    boolean deleteManager(String mgrId);

    //根据id查询管理员信息
    Manager getManagerById(String mgrId);
    //修改管理员信息
    boolean updateManager(Manager manager, int roleId);
}
