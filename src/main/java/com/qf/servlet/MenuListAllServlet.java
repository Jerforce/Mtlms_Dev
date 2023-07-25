package com.qf.servlet; /**
 * @author Jerforce
 * @date 2023/7/20 星期四 11:59:13
 */

import com.qf.entity.Menu1;
import com.qf.service.MenuService;
import com.qf.service.impl.MenuServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "MenuListAllServlet", value = "/MenuListAllServlet")
public class MenuListAllServlet extends HttpServlet {
    MenuService menuService = new MenuServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.查询所有的系统菜单
        List<Menu1> menu1List = menuService.listAllMenus();
        //2.将查询到的系统菜单集合传递到 admin_role_add.jsp
        request.setAttribute("menu1List",menu1List);
        request.getRequestDispatcher("admin_role_add.jsp").forward(request,response);
    }
}
