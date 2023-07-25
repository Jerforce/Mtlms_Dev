package com.qf.servlet; /**
 * @author Jerforce
 * @date 2023/7/20 星期四 11:30:09
 */

import com.qf.entity.Role;
import com.qf.service.RoleService;
import com.qf.service.impl.RoleServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "RoleListServlet", value = "/RoleListServlet")
public class RoleListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.查询所有的角色信息
        RoleService roleService = new RoleServiceImpl();
        List<Role> roleList = roleService.getRoles();
        //2.将角色列表传递到 admin_role_list.jsp
        request.setAttribute("roleList", roleList);
        request.getRequestDispatcher("admin_role_list.jsp").forward(request, response);
    }
}
