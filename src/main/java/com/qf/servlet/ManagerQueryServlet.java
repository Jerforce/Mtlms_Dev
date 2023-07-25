package com.qf.servlet; /**
 * @author Jerforce
 * @date 2023/7/20 星期四 15:59:34
 */

import com.google.gson.Gson;
import com.qf.entity.Manager;
import com.qf.service.ManagerService;
import com.qf.service.RoleService;
import com.qf.service.impl.ManagerServiceImpl;
import com.qf.service.impl.RoleServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ManagerQueryServlet", value = "/ManagerQueryServlet")
public class ManagerQueryServlet extends HttpServlet {
    ManagerService managerService = new ManagerServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取管理员ID
        String mgrId = request.getParameter("managerId");

        //2.调用managerService查询管理员原始信息
        Manager manager = managerService.getManagerById(mgrId);
        String jsonStr = new Gson().toJson(manager);

        //3.响应ajax请求
        response.setContentType("application/json;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        out.print(jsonStr);
        out.flush();
        out.close();
    }
}
