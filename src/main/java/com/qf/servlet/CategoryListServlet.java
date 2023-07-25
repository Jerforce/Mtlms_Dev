package com.qf.servlet;

import com.qf.dao.CategoryDao;
import com.qf.dao.impl.CategoryDaoImpl;
import com.qf.entity.Category;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CategoryListServlet", value = "/CategoryListServlet")
public class CategoryListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    //分类管理的显示数据页面【查找全部一级菜单】
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CategoryDao dao = new CategoryDaoImpl();
        //查询全部的一级菜单
        List<Category> all = dao.findAll();
        System.out.println(all);
        //跳转
        request.setAttribute("categoryList", all);
        request.getRequestDispatcher("category_list.jsp").forward(request, response);

    }
}
