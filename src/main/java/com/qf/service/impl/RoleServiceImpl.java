package com.qf.service.impl;

import com.qf.dao.RoleDao;
import com.qf.dao.impl.RoleDaoImpl;
import com.qf.entity.Role;
import com.qf.service.RoleService;

import java.util.List;

/**
 * @author Jerforce
 * @date 2023/7/20 星期四 11:14:24
 */
public class RoleServiceImpl implements RoleService {
    RoleDao roleDAO = new RoleDaoImpl();
    @Override
    public List<Role> getRoles() {
        List<Role> roleList = roleDAO.selectRoles();
        return roleList;
    }

    @Override
    public boolean addRole(Role role, String[] menuIds) {
        boolean b = true;
        //1.保存角色信息，获取生成的角色的ID
        int roleId = roleDAO.insertRole(role);
        //2.保存角色和菜单的关联
        if(menuIds != null){
            for (int i = 0; i < menuIds.length; i++) {
                int meunId = Integer.parseInt(menuIds[i]);
                int j = roleDAO.insertRoleAndMenu(roleId, meunId);
                b = b&& j>0;
            }
        }
        return b;
    }

    @Override
    public boolean deleteRole(int roleId) {
        int i = roleDAO.deleteRoleAndMenu(roleId);
        int j = roleDAO.deleteRole(roleId);
        boolean b = j>0;
        return b;
    }

    @Override
    public Role getRoleById(int roleId) {
       return roleDAO.selectRoleById(roleId);
    }

    @Override
    public List<Integer> getMenuIdsByRole(int roleId) {
        return roleDAO.selectMenuIdsByRoleId(roleId);
    }

    @Override
    public boolean updateRole(Role role, String[] menuIds) {
        int i = roleDAO.updateRole(role);

        //2.删除当前角色的原始权限
        int j = roleDAO.deleteRoleAndMenu(role.getRoleId());

        //3.新增选择的所有权限
        for (int k = 0; k < menuIds.length ; k++) {
            int menuId = Integer.parseInt( menuIds[k] );
            int m = roleDAO.insertRoleAndMenu(role.getRoleId(), menuId);
        }
        //对于修改角色而言，角色是可以没有权限菜单的，因此只要i>0就表示角色修改成功
        return i>0;
    }
}
