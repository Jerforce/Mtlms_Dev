package com.qf.servlet; /**
 * @author Jerforce
 * @date 2023/7/20 星期四 11:27:40
 */

import com.qf.service.RoleService;
import com.qf.service.impl.RoleServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "RoleDeleteServlet", value = "/RoleDeleteServlet")
public class RoleDeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.接收角色ID
        int roleId = Integer.parseInt( request.getParameter("roleId") );
        //2.调用RoleService执行删除
        RoleService RoleService = new RoleServiceImpl();
        boolean b = RoleService.deleteRole(roleId);
        //3.删除成功之后，响应前端的ajax请求
        String str = b?"{\"code\":1000,\"msg\":\"success\"}" : "{\"code\":1001,\"msg\":\"fail\"}";
        response.setContentType("application/json;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        out.println(str);
        out.flush();
        out.close();
    }
}
