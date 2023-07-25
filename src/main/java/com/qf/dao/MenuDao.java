package com.qf.dao;

import com.qf.entity.Menu1;
import com.qf.entity.Menu2;

import java.util.List;

public interface MenuDao {
    //根据mgr_id查询所有的一级菜单
    List<Menu1> findByMId(String mId);
    //根据管理者的id和parent_menu_code是一级列表的二级菜单--查询二级菜单
   List<Menu2> findByMIdMIdAndPId(String mId,String PId);
    ////查询所有的一级菜单
    List<Menu1> selectMenu1();
    ////查询所有的二级菜单
    List<Menu2> selectMenu2();
    //根据一级菜单的menuCode查询当前一级菜单下的二级菜单
    List<Menu2> selectMenu2ByMenu1Code(String parentCode);
    //根据menuCode修改菜单状态
    int updateMenuState(String menuCode,int state);



}
