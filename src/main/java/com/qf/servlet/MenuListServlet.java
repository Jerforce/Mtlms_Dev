package com.qf.servlet; /**
 * @author Jerforce
 * @date 2023/7/20 星期四 12:00:17
 */

import com.qf.service.MenuService;
import com.qf.service.impl.MenuServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "MenuListServlet", value = "/MenuListServlet")
public class MenuListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.查询一级菜单和二级菜单
        MenuService menuService = new MenuServiceImpl();
        Map<String, List> menus = menuService.listMenus();
        //2.将一级菜单和二级菜单传递到 admin_menu_list.jsp
        request.setAttribute("menu1List",menus.get("menu1List"));
        request.setAttribute("menu2List",menus.get("menu2List"));
        request.getRequestDispatcher("admin_menu_list.jsp").forward(request,response);
    }
}
