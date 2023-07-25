package com.qf.servlet; /**
 * @author Jerforce
 * @date 2023/7/20 星期四 11:55:12
 */

import com.qf.entity.Role;
import com.qf.service.MenuService;
import com.qf.service.RoleService;
import com.qf.service.impl.MenuServiceImpl;
import com.qf.service.impl.RoleServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "RoleUpdateServlet", value = "/RoleUpdateServlet")
public class RoleUpdateServlet extends HttpServlet {
    private RoleService roleService = new RoleServiceImpl();
    private MenuService menuService = new MenuServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.接收修改后的角色信息
        request.setCharacterEncoding("UTF-8");
        int roleId = Integer.parseInt( request.getParameter("roleId") );
        String roleName = request.getParameter("roleName");
        String roleDesc = request.getParameter("roleDesc");
        Role role = new Role(roleId, roleName, roleDesc);

        //2.获取传递过来的选择的菜单的ID
        String[] menuIds = request.getParameterValues("menuId");

        //3.执行修改
        boolean b = roleService.updateRole(role, menuIds);

        //4.跳转到提示页面进行提示
        String tips = b?"<label style='color:green'>修改角色信息成功！</label>"
                :"<label style='color:red'>修改角色信息失败！</label>";
        request.setAttribute("tips",tips);
        request.getRequestDispatcher("prompt.jsp").forward(request,response);
    }
}
