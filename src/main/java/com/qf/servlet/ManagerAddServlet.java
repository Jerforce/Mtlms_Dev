package com.qf.servlet; /**
 * @author Jerforce
 * @date 2023/7/20 星期四 15:44:18
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
import java.util.Date;
import java.util.List;

@WebServlet(name = "ManagerAddServlet", value = "/ManagerAddServlet")
public class ManagerAddServlet extends HttpServlet {
    ManagerService managerService = new ManagerServiceImpl();
    RoleService roleService = new RoleServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.接收输入的管理员信息、选择的角色ID
        request.setCharacterEncoding("utf-8");
        String mgrId = request.getParameter("mgrId");
        String loginName = request.getParameter("loginName");
        String loginPwd = request.getParameter("loginPwd");
        String mgrName = request.getParameter("mgrName");
        String mgrGender = request.getParameter("mgrGender");
        String mgrTel = request.getParameter("mgrTel");
        String mgrEmail = request.getParameter("mgrEmail");
        String mgrQQ = request.getParameter("mgrQQ");
        Manager manager = new Manager(mgrId,loginName,loginPwd,mgrName,mgrGender,mgrTel,mgrEmail,mgrQQ,new Date());

        int roleId = Integer.parseInt( request.getParameter("roleId") );

        //2.调用ManagerService保存管理员信息
        boolean b = managerService.saveManager(manager, roleId);

        //3.返回到列表页面（？？？）
        String tips = b?"<label style='color:green'>管理员添加成功！</label>"
                :"<label style='color:red'>管理员添加失败！</label>";
        //当再次回到admin_manager_list.jsp页面时，依然需要管理员列表和角色列表
        List<Manager> managerList = managerService.listManagers();
        List<Role> roleList = roleService.getRoles();

        request.setAttribute("tips",tips);
        request.setAttribute("managerList",managerList);
        request.setAttribute("roleList",roleList);
        request.getRequestDispatcher("admin_manager_list.jsp").forward(request,response);
    }
}
