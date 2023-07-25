package com.qf.servlet; /**
 * @author Jerforce
 * @date 2023/7/20 星期四 10:38:16
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

@WebServlet(name = "RoleAddServlet", value = "/RoleAddServlet")
public class RoleAddServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1.接收角色名称和角色描述
        request.setCharacterEncoding("utf-8");
        String roleName = request.getParameter("roleName");
        String roleDesc = request.getParameter("roleDesc");
        Role role = new Role(0, roleName, roleDesc);
        //2.获取选择的菜单权限的id
        String[] menuIds = request.getParameterValues("menuId");
        //3.保存角色信息
        RoleService roleService = new RoleServiceImpl();
        boolean b = roleService.addRole(role, menuIds);
        //4.角色添加成功之后，跳到 提示页面  prompt.jsp 并提示操作结果
        String tips = b ? "<label style='color:green'>添加角色信息成功！</label>" :
                "<label style='color:red'>添加角色信息失败！</label>";
        request.setAttribute("tips", tips);
        request.getRequestDispatcher("prompt.jsp").forward(request, response);
        //如果添加成功不需要单独的提示页面，也可以跳转到RoleListServlet，从而进入到角色列表页面
    }
}

