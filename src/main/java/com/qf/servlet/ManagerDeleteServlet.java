package com.qf.servlet; /**
 * @author Jerforce
 * @date 2023/7/20 星期四 15:52:39
 */

import com.qf.service.ManagerService;
import com.qf.service.RoleService;
import com.qf.service.impl.ManagerServiceImpl;
import com.qf.service.impl.RoleServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ManagerDeleteServlet", value = "/ManagerDeleteServlet")
public class ManagerDeleteServlet extends HttpServlet {
    ManagerService managerService = new ManagerServiceImpl();
    RoleService roleService = new RoleServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.接收管理员ID
        String mgrId = request.getParameter("mgrId");
        //2.执行删除
        boolean b = managerService.deleteManager(mgrId);
        //3.响应ajax请求（响应json格式数据）
        String jsonStr = b?"{\"code\":1000,\"msg\":\"success\"}"
                :"{\"code\":1001,\"msg\":\"fail\"}";
        response.setContentType("application/json;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        out.println(jsonStr);
        out.flush();
        out.close();
    }
}
