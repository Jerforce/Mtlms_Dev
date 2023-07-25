package com.qf.servlet;

import com.qf.dao.CategoryDao;
import com.qf.dao.impl.CategoryDaoImpl;
import com.qf.entity.Category;
import com.qf.service.CategoryService;
import com.qf.service.impl.CategoryServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CategoryQueryServlet", value = "/CategoryQueryServlet")
public class CategoryQueryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CategoryDao dao=new CategoryDaoImpl();
        //获得前端category_list.jsp的值
        String cid = request.getParameter("cid");
        int i = Integer.parseInt(cid);
        CategoryService service=new CategoryServiceImpl();
        Category category = service.queryACategory(i);
        //把值传给修改页面
        request.setAttribute("category",category);
        //转发
        request.getRequestDispatcher("category_modify.jsp").forward(request,response);
    }
}
