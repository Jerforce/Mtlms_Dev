package com.qf.servlet;

import com.qf.entity.Manager;
import com.qf.entity.Menu1;
import com.qf.service.MenuService;
import com.qf.service.impl.MenuServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "IndexServlet", value = "/IndexServlet")
public class IndexServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //查询全部菜单信息
        MenuService service=new MenuServiceImpl();
        Manager mgr = (Manager) request.getSession().getAttribute("mgr");
        List<Menu1> menu1List = service.findByMId(mgr.getMgrId());
        request.setAttribute("menu1List",menu1List);
        request.getRequestDispatcher("index.jsp").forward(request,response);
        //把菜单信息放到request里
        //跳转到index.jsp中
    }
}
