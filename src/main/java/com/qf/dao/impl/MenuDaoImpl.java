package com.qf.dao.impl;

import com.qf.dao.MenuDao;
import com.qf.entity.Menu1;
import com.qf.entity.Menu2;
import com.qf.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MenuDaoImpl implements MenuDao {

    QueryRunner runner = new QueryRunner(DruidUtils.getDruidDataSource());

    //查询全部的一级菜单
    @Override
    public List<Menu1> findByMId(String mId) {
        String sql = "select rm.menu_id menuId,menu_code menuCode,menu_name menuName,\n" +
                "menu_order menuOrder,menu_level menuLevel,menu_icon menuIcon,\n" +
                "menu_state menuState\n" +
                "from tb_mgr_role mr join tb_role_menu rm join tb_menus m\n" +
                "on mr.role_id=rm.role_id and rm.menu_id=m.menu_id\n" +
                "where menu_level=1 and mgr_id=? \n" +
                "order by menu_order\n";

        List<Menu1> query = null;
        try {
            query = runner.query(sql, new BeanListHandler<>(Menu1.class), mId);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return query;
    }


    //查询二级菜单
    @Override
    public List<Menu2> findByMIdMIdAndPId(String mId, String PId) {
        String sql = "select rm.menu_id menuId,menu_code menuCode,menu_name menuName,\n" +
                "menu_order menuOrder,menu_level menuLevel,\n" +
                "parent_menu_code parentMenuCode,menu_url menuUrl,menu_state menuState\n" +
                "from tb_mgr_role mr join tb_role_menu rm join tb_menus m\n" +
                "on mr.role_id=rm.role_id and rm.menu_id=m.menu_id\n" +
                "where menu_level=2 and mgr_id=? and parent_menu_code=?\n" +
                "order by menu_order";

        List<Menu2> query = null;
        try {
            query = runner.query(sql, new BeanListHandler<>(Menu2.class), mId, PId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return query;
    }

    @Override
    public List<Menu1> selectMenu1() {
        List<Menu1> menu1List = new ArrayList<>();
        try {
            String sql = "select menu_id menuId,menu_code menuCode,menu_name menuName,menu_order menuOrder,menu_level menuLevel,menu_icon menuIcon " +
                    "from tb_menus where menu_level=1 order by menu_order;";
            menu1List = runner .query(sql,new BeanListHandler<Menu1>(Menu1.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menu1List;
    }

    @Override
    public List<Menu2> selectMenu2() {
        List<Menu2> menu2List = new ArrayList<>();
        try {
            String sql = "select menu_id menuId,menu_code menuCode,menu_name menuName,menu_order menuOrder,menu_level menuLevel,parent_menu_code parentMenuCode,menu_url menuUrl,menu_state menuState " +
                    "from tb_menus where menu_level=2 order by menu_order";
            menu2List = runner.query(sql, new BeanListHandler<Menu2>(Menu2.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menu2List;
    }

    @Override
    public List<Menu2> selectMenu2ByMenu1Code(String parentCode) {
        List<Menu2> menu2List = new ArrayList<>();
        try {
            String sql = "select menu_id menuId,menu_code menuCode,menu_name menuName,menu_order menuOrder,menu_level menuLevel," +
                    "parent_menu_code parentMenuCode,menu_url menuUrl,menu_state menuState " +
                    "from tb_menus where parent_menu_code=? order by menu_order";
            menu2List = runner.query(sql, new BeanListHandler<Menu2>(Menu2.class), parentCode);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menu2List;
    }

    @Override
    public int updateMenuState(String menuCode, int state) {
        int i = 0;
        try {
            String sql ="update tb_menus set menu_state=? where menu_code=?";
            Object[] params = {state,menuCode};
               i = runner.update(sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }


}
