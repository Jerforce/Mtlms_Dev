package com.qf.service.impl;

import com.qf.dao.MenuDao;
import com.qf.dao.impl.MenuDaoImpl;
import com.qf.entity.Menu1;
import com.qf.entity.Menu2;
import com.qf.service.MenuService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//查询一级查单，调用查询二级菜单把二级菜单存到一级菜单中
public class MenuServiceImpl implements MenuService {

    MenuDao  menuDAO = new MenuDaoImpl();

    @Override
    public List<Menu1> findByMId(String mId) {
        //获得全部的一级菜单
        List<Menu1> menu1List =  menuDAO.findByMId(mId);
        for (Menu1 menu1 : menu1List) {
            //二级菜单的编号
            List<Menu2> menu2List =  menuDAO.findByMIdMIdAndPId(mId, menu1.getMenuCode());
            menu1.setChildMenus(menu2List);
        }
        return menu1List;
    }

    @Override
    public Map<String, List> listMenus() {
        //1.查询一级菜单

        List<Menu1> menu1List = menuDAO.selectMenu1();
        //2.查询二级菜单
        List<Menu2> menu2List = menuDAO.selectMenu2();
        //3.将menu1List和menu2List存储到map中
        Map<String,List> menus = new HashMap<>();
        menus.put("menu1List",menu1List);
        menus.put("menu2List",menu2List);
        return menus;
    }

    @Override
    public List<Menu2> listMenu2ByMenu1Code(String menu1Code) {
        List<Menu2> menu2List = menuDAO.selectMenu2ByMenu1Code(menu1Code);
        return  menu2List;
    }

    @Override
    public boolean enableMenu(String menuCode) {
        return changeMenuState(menuCode,1);
    }

    @Override
    public boolean disableMenu(String menuCode) {
        return changeMenuState(menuCode,0);
    }

    @Override
    public boolean changeMenuState(String menuCode, int state) {
        int i = menuDAO.updateMenuState(menuCode, state);
        boolean b = i>0;
        return b;
    }

    @Override
    public List<Menu1> listAllMenus() {
        //1.查询到所有的一级菜单（此时的一级菜单中是不包含二级菜单的）
        List<Menu1> menu1List = menuDAO.selectMenu1();

        //2.遍历已经查询到的一级菜单，依次查询每个一级菜单中的二级菜单
        for (int i = 0; i < menu1List.size(); i++) {
            Menu1 menu1 = menu1List.get(i);
            //根据一级菜单的menuCode查询二级菜单
            List<Menu2> menu2List = menuDAO.selectMenu2ByMenu1Code(menu1.getMenuCode());
            //将查询到二级菜单设置到当前一级菜单对象中
            menu1.setChildMenus(menu2List);
        }
        return menu1List;
    }
}
