package com.qf.dao;

import com.qf.entity.Role;

import java.util.List;

/**
 * @author Jerforce
 * @date 2023/7/20 星期四 10:23:13
 */
public interface RoleDao {
    // 查询所有的角色信息
    List<Role> selectRoles();

    //添加角色信息(获取添加的数据自动生成的主键)
    int insertRole(Role role);
    //添加角色和菜单的关联关系
    int insertRoleAndMenu(int roleId,int menuId);
    // 根据角色ID删除这个角色与菜单的映射
    int deleteRoleAndMenu(int roleId);
    //根据角色ID删除角色信息
    int deleteRole(int roleId);
    //根据角色ID查询角色信息
    Role selectRoleById(int roleId);
    //根据角色ID查询当前角色所拥有的权限ID
    List<Integer> selectMenuIdsByRoleId(int roleId);
    //根据角色id修改角色名称及角色描述
    int updateRole(Role role);

}
