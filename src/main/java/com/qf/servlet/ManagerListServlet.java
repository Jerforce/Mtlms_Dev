package com.qf.servlet; /**
 * @author Jerforce
 * @date 2023/7/20 星期四 15:58:06
 */

import com.qf.entity.Manager;
import com.qf.entity.Role;
import com.qf.service.ManagerService;
import com.qf.service.RoleService;
import com.qf.service.impl.ManagerServiceImpl;
import com.qf.service.impl.RoleServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ManagerListServlet", value = "/ManagerListServlet")
public class ManagerListServlet extends HttpServlet {
    ManagerService managerService = new ManagerServiceImpl();
    RoleService roleService = new RoleServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.查询管理员信息列表
        List<Manager> managerList = managerService.listManagers();
        //2.查询角色信息列表
        List<Role> roleList = roleService.getRoles();
        //3.将管理员信息列表及角色信息列表传递到admin_manager_list.jsp
        request.setAttribute("managerList",managerList);
        request.setAttribute("roleList",roleList);
        request.getRequestDispatcher("admin_manager_list.jsp").forward(request,response);
    }
}
