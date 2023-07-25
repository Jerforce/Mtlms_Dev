package com.qf.servlet;

import com.qf.entity.Category;
import com.qf.service.CategoryService;
import com.qf.service.impl.CategoryServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CategoryLoadServlet", value = "/CategoryLoadServlet")
public class CategoryLoadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获得brand_add.jsp前端传过来的值
        String categoryId = request.getParameter("categoryId");
        CategoryService service = new CategoryServiceImpl();
        //查询全部的一级菜单
        List<Category> categoryList = service.findAll();
        request.setAttribute("categoryList", categoryList);
//        //获得品牌列表
//        String brandName = request.getParameter("brandName");
//        //获得隐藏域的地址
//        String brandLogoPath = request.getParameter("brandLogoPath");
//        //获得前端的文字输入框
//        String brandDesc = request.getParameter("brandDesc");
//        //获得显示状态
//        String brandStatus = request.getParameter("brandStatus");


        request.getRequestDispatcher("brand_add.jsp").forward(request, response);
    }
}
