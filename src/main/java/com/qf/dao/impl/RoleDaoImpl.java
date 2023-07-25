package com.qf.dao.impl;

import com.qf.dao.RoleDao;
import com.qf.entity.Role;
import com.qf.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jerforce
 * @date 2023/7/20 星期四 10:26:55
 */
public class RoleDaoImpl implements RoleDao {
    QueryRunner queryRunner = new QueryRunner(DruidUtils.getDruidDataSource());
    @Override
    public List<Role> selectRoles() {
        //1.创建一个List集合，用于存储查询到的角色信息
        List<Role> roleList = new ArrayList<>();
        try {
            String sql = "select role_id roleId,role_name roleName,role_desc roleDesc from tb_roles";

            roleList = queryRunner.query(sql, new BeanListHandler<Role>(Role.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
       return roleList;
    }


    @Override
    public int insertRole(Role role) {
        int i = 0;
        try {
            String sql = "insert into tb_roles(role_name,role_desc) values(?,?)";
            //返回的生成的主键存储在一个BigInteger对象中
            BigInteger object = (BigInteger) queryRunner.insert(sql, new ScalarHandler<>(), role.getRoleName(), role.getRoleDesc());
            //将BigInteger转换成int类型，赋值给i
            i = object.intValue();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    @Override
    public int insertRoleAndMenu(int roleId, int menuId) {
        int i = 0;
        try {
            String sql = "insert into tb_role_menu(role_id,menu_id) values(?,?)";
            i = queryRunner.update(sql, roleId, menuId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    @Override
    public int deleteRoleAndMenu(int roleId){
    int i = 0;
        try {
        String sql = "delete from tb_role_menu where role_id=?";
        i = queryRunner.update(sql, roleId);
    } catch (SQLException e) {
        e.printStackTrace();
    }
        return i;
}

    @Override
    public int deleteRole(int roleId){
    int i = 0;
        try {
        String sql = "delete from tb_roles where role_id=?";
        i = queryRunner.update(sql, roleId);
    } catch (SQLException e) {
        e.printStackTrace();
    }
        return i;
}

    @Override
    public Role selectRoleById(int roleId) {
        Role role = null;
        try {
            String sql = "select role_id roleId,role_name roleName,role_desc roleDesc from tb_roles where role_id=?";
            role = queryRunner.query(sql, new BeanHandler<Role>(Role.class),roleId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return role;

    }

    @Override
    public List<Integer> selectMenuIdsByRoleId(int roleId) {

        List<Integer> mids = new ArrayList<>();
        try {
            String sql = "select menu_id from tb_role_menu where role_id=?";
            //自定义结果集处理器
            ResultSetHandler<List<Integer>> resultSetHandler = new ResultSetHandler<List<Integer>>() {
                @Override
                public List<Integer> handle(ResultSet resultSet) throws SQLException {
                    List<Integer> list = new ArrayList<>();
                    while(resultSet.next()){
                        int mid = resultSet.getInt("menu_id");
                        list.add(mid);
                    }
                    return list;
                }
            };
            mids = queryRunner.query(sql,resultSetHandler,roleId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mids;
    }



    @Override
    public int updateRole(Role role) {
        int i = 0;
        try {
            String sql = "update tb_roles set role_name=?,role_desc=? where role_id=?";
            Object[] params = {role.getRoleName(),role.getRoleDesc(),role.getRoleId()};
            i = queryRunner.update(sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }
}
