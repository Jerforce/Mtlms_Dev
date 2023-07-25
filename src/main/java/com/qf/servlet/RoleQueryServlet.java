package com.qf.servlet; /**
 * @author Jerforce
 * @date 2023/7/20 星期四 11:32:18
 */

import com.qf.entity.Menu1;
import com.qf.entity.Menu2;
import com.qf.entity.Role;
import com.qf.service.MenuService;
import com.qf.service.RoleService;
import com.qf.service.impl.MenuServiceImpl;
import com.qf.service.impl.RoleServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "RoleQueryServlet", value = "/RoleQueryServlet")
public class RoleQueryServlet extends HttpServlet {
    private RoleService roleService = new RoleServiceImpl();
    private MenuService menuService = new MenuServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.接收角色ID
        String rid = request.getParameter("roleId");
        int roleId = rid==null?0:Integer.parseInt(rid);

        //2.根据角色ID查询角色的原始信息
        Role role = roleService.getRoleById(roleId);

        //3.查询系统中的权限菜单列表
        //a.查询所有菜单
        List<Menu1> menu1List = menuService.listAllMenus();
        //b.查询当前角色拥有的菜单的id
        List<Integer> menuIds = roleService.getMenuIdsByRole(roleId);
        //c.判断所有菜单中，哪些菜单是当前角色拥有的
        for (int i = 0; i <menu1List.size() ; i++) {
            Menu1 menu1 = menu1List.get(i);
            //如果menuIds中包含当前一级菜单的id，说明当前角色具有这个一级菜单
            //那么如何标识这个一级权限的状态呢？ —— 在Menu1实体类中添加一个标识属性
            if(menuIds.contains(menu1.getMenuId())){
                menu1.setHaveMenu(true);
            }
            //判断二级权限
            for (int j = 0; j < menu1.getChildMenus().size() ; j++) {
                Menu2 menu2 = menu1.getChildMenus().get(j);
                if(menuIds.contains(menu2.getMenuId())){
                    menu2.setHaveMenu(true);
                }
            }
        }
        //【说明】通过以上for判断及设置，menu1List中当前角色拥有的菜单haveMenu属性都为true

        //4.将角色信息 及  菜单集合 传递到 admin_role_modify.jsp
        request.setAttribute("role",role);
        request.setAttribute("menu1List", menu1List);
        request.getRequestDispatcher("admin_role_modify.jsp").forward(request,response);
    }
}

