package com.qf.service;

import com.qf.entity.Menu1;
import com.qf.entity.Menu2;

import java.util.List;
import java.util.Map;

public interface MenuService {

   /*
   * //根据mgr_id查询所有的一级菜单
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

   * */

    //根据mgr_id查询全部的菜单包含一级和二级（每一条一级菜单保函二级菜单）
    List<Menu1> findByMId(String mId);
    //查询一级菜单和二级菜单 |||  map集合中包含一级菜单和二级菜单
    Map<String,List> listMenus();
    //根据一级菜单menuCode查询二级菜单
    List<Menu2> listMenu2ByMenu1Code(String menu1Code);
    //启用菜单
    boolean enableMenu(String menuCode);
    //停用菜单
    boolean disableMenu(String menuCode);
    //更改菜单状态
    boolean changeMenuState(String menuCode,int state);
    //查询所有的一级菜单，并且在 （每） 个一级菜单中包含其对应的二级菜单
    List<Menu1> listAllMenus();


}
