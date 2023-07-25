package com.qf.servlet;

import com.qf.entity.BasicInfo;
import com.qf.service.BasicInfoService;
import com.qf.service.impl.BasicInfoServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "BasicInfoListServlet", value = "/BasicInfoListServlet")
public class BasicInfoListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获得数据
        BasicInfoService service=new BasicInfoServiceImpl();
        List<BasicInfo> basicInfoList = service.findAll();
        //将数据放入request
        request.setAttribute("basicInfoList",basicInfoList);
        //转发
        request.getRequestDispatcher("products_add.jsp").forward(request,response);
    }
}
