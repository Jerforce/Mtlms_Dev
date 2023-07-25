package com.qf.servlet; /**
 * @author Jerforce
 * @date 2023/7/20 星期四 16:00:18
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

@WebServlet(name = "ManagerUpdateServlet", value = "/ManagerUpdateServlet")
public class ManagerUpdateServlet extends HttpServlet {
    ManagerService managerService = new ManagerServiceImpl();
    RoleService roleService = new RoleServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.接收管理员修改后的信息
        request.setCharacterEncoding("utf-8");
        String mgrId = request.getParameter("mgrId");  //原始管理员ID
        String loginName = request.getParameter("loginName");
        String loginPwd = request.getParameter("loginPwd");
        String mgrName = request.getParameter("mgrName");
        String mgrGender = request.getParameter("mgrGender");
        String mgrTel = request.getParameter("mgrTel");
        String mgrEmail = request.getParameter("mgrEmail");
        String mgrQQ = request.getParameter("mgrQQ");
        Manager manager = new Manager(mgrId,loginName,loginPwd,mgrName,mgrGender,mgrTel,mgrEmail,mgrQQ,new Date());

        //2.接收选择的角色ID
        int roleId = Integer.parseInt( request.getParameter("roleId") );

        //3.执行修改
        boolean b = managerService.updateManager(manager, roleId);

        //4.修改完成之后跳转到管理员列表页面（需要重新查询管理员列表和角色列表）
        String tips = b?"<label style='color:green'>管理员修改成功！</label>"
                :"<label style='color:red'>管理员修改失败！</label>";
        //当再次回到admin_manager_list.jsp页面时，依然需要管理员列表和角色列表
        List<Manager> managerList = managerService.listManagers();
        List<Role> roleList = roleService.getRoles();

        request.setAttribute("tips",tips);
        request.setAttribute("managerList",managerList);
        request.setAttribute("roleList",roleList);
        request.getRequestDispatcher("admin_manager_list.jsp").forward(request,response);
    }
}
