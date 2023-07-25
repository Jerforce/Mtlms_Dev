package com.qf.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor

//tb_menus表中数据
public class Menu1 {
   private int menuId;
   private String menuCode;
   private String menuName;
   private int menuOrder;
   private int menuLevel;
   private String menuIcon;
   private int menuState;
   //二级菜单的集合  一级菜单包含多条二级菜单
   private List<Menu2> childMenus;
   private boolean haveMenu;  //用于标识当前角色是由拥有该权限菜单

   public boolean getHaveMenu() {
      return haveMenu;
   }
}
