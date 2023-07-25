package com.qf.service;

import com.qf.dao.RoleDao;
import com.qf.entity.Role;

import java.util.List;

/**
 * @author Jerforce
 * @date 2023/7/20 星期四 11:03:07
 */
public interface RoleService {
    List<Role> getRoles();

    boolean addRole(Role role,String[] menuIds);


    boolean deleteRole(int roleId);

    Role getRoleById(int roleId);

    List<Integer> getMenuIdsByRole(int roleId);
    public boolean updateRole(Role role,String[] menuIds);


}
