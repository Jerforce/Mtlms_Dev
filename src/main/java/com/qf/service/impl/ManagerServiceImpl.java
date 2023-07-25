package com.qf.service.impl;

import com.qf.dao.ManagerDao;
import com.qf.dao.impl.ManagerDaoImpl;
import com.qf.entity.Manager;
import com.qf.service.ManagerService;
import com.qf.utils.MD5Utils;

import java.util.List;

public class ManagerServiceImpl implements ManagerService {

    ManagerDao managerDAO = new ManagerDaoImpl();

    //tb_managers表中的名字和密码去找人【1】
    @Override
    public Manager login(String loginName, String loginPwd) {
        //把传过来的密码进行加密
        loginPwd = MD5Utils.md5Encode(loginPwd);
        //登录方法
        return managerDAO.findByNameAndPwd(loginName, loginPwd);
    }

    @Override
    public List<Manager> listManagers() {
        //查询所有管理员信息
        List<Manager> managerList = managerDAO.selectManagers();
        return managerList;
    }

    @Override
    public boolean saveManager(Manager manager, int roldId) {
        //1.对manager对象中的密码进行MD5加密处理（数据库中密码要保存密文）
        String s = MD5Utils.md5Encode(manager.getLoginPwd());
        manager.setLoginPwd(s);
        //2.保存管理员信息
        int i = managerDAO.insertManager(manager);
        //3.保存管理员和角色关联关系
        int j = managerDAO.insertMgrAndRole(manager.getMgrId(), roldId);
        return i > 0;
    }

    @Override
    public boolean deleteManager(String mgrId) {
        //1.删除管理员与角色的关联关系
        int i = managerDAO.deleteMgrAndRole(mgrId);
        //2.删除管理员信息
        int j = managerDAO.deleteManager(mgrId);
        return j > 0;
    }

    @Override
    public Manager getManagerById(String mgrId) {
        //根据管理员ID查询管理员信息
        Manager manager = managerDAO.selectManagerById(mgrId);
        return manager;
    }

    @Override
    public boolean updateManager(Manager manager, int roleId) {
        //1.修改管理员密码 （管理员有密码：如果没有修改密码，则密码为加密状态； 如果管理员修改了密码，则密码为明文）
        //  a.如果在修改页面没有输入密码，则认为不修改密码
        //  b.如果在修改页面输入了密码，则表示需要将密码修改到数据库
        if (manager.getLoginPwd() == null || "".equals(manager.getLoginPwd())) {
            //将此管理员的原始密码设置到manager中
            Manager oldManager = managerDAO.selectManagerById(manager.getMgrId());
            manager.setLoginPwd(oldManager.getLoginPwd());
        } else {
            //如果在修改页面输入了密码，则对新密码进行加密
            String s = MD5Utils.md5Encode(manager.getLoginPwd());
            manager.setLoginPwd(s);
        }  //2.执行修改管理员
        int i = managerDAO.updateManager(manager);

        //3.修改管理员的角色
        if(i>0){
            //a.删除当前管理员的原始角色关系关联  tb_mgr_role
            int j = managerDAO.deleteMgrAndRole(manager.getMgrId());

            //b.添加新的管理员与角色的关联关系
            int k = managerDAO.insertMgrAndRole(manager.getMgrId(),roleId);
        }

        return i>0;
    }
}
