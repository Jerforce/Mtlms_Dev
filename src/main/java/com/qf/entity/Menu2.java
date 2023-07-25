package com.qf.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
//二级菜单
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Menu2 {
    private int menuId;
    private String menuCode;
    private String menuName;
    private int menuOrder;
    private int menuLevel;
    private String parentMenuCode;
    private String menuUrl;
    private int menuState;
    private boolean haveMenu;
}
