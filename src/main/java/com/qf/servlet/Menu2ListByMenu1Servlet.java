package com.qf.servlet; /**
 * @author Jerforce
 * @date 2023/7/20 星期四 11:57:49
 */

import com.google.gson.Gson;
import com.qf.entity.Menu2;
import com.qf.service.MenuService;
import com.qf.service.impl.MenuServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "Menu2ListByMenu1Servlet", value = "/Menu2ListByMenu1Servlet")
public class Menu2ListByMenu1Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.接收一级菜单菜单编号
        String parentCode = request.getParameter("parentCode");
        //2.根据parentCode查询二级菜单
        MenuService menuService = new MenuServiceImpl();
        List<Menu2> menu2List = menuService.listMenu2ByMenu1Code(parentCode);
        //3.将menu2List集合以JSON格式响应给页面
        // a.将menu2List集合转换成JSON格式（使用GSON，条件GSON的依赖）
        Gson gson = new Gson();
        String jsonStr = gson.toJson(menu2List);
        // b.在servlet类通过输出流响应ajax请求
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println(jsonStr);
        out.flush();
        out.close();
    }
}
