package com.qf.servlet;

import com.qf.dao.ManagerDao;
import com.qf.entity.Manager;
import com.qf.service.ManagerService;
import com.qf.service.impl.ManagerServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

//判断验证码，密码，账号是否正确
@WebServlet(name = "ManagerLoginServlet", value = "/ManagerLoginServlet")
public class ManagerLoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获得用户名和密码
        String loginName = request.getParameter("loginName");
        String loginPwd = request.getParameter("loginPwd");
        String checkCode = request.getParameter("checkCode");
        //获得自动生成的验证码
        HttpSession session = request.getSession();
        String vCode = (String) session.getAttribute("vCode");
        //判断验证码是否正确
        if (vCode.equalsIgnoreCase(checkCode)) {
            //验证码正确的情况下,判断账号密码是否真确
            ManagerService service = new ManagerServiceImpl();
            //判断密码和账号
            Manager manager = service.login(loginName, loginPwd);
            System.out.println(manager);
            if (manager != null) {
                //把值放到session中
                session.setAttribute("mgr", manager);
                //重定向
                response.sendRedirect("IndexServlet");
            } else {
                //设置响应内容
                request.setAttribute("tips", "用户名和密码有误！");
                //跳转【转发】
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }


        } else {
            //设置响应内容
            request.setAttribute("tips", "验证码有误！");
            //跳转【转发】
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }

        //2.先判断验证码是否跟自动生成一致
        //3.如果一直在判断用户名和密码是否正确--->成功跳到index.jsp 失败就login.jsp
        //4.如果不一致就返回到登录主页
    }
}
