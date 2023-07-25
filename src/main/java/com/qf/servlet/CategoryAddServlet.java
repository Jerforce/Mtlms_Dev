package com.qf.servlet;

import com.qf.entity.Category;
import com.qf.service.CategoryService;
import com.qf.service.impl.CategoryServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@WebServlet(name = "CategoryAddServlet", value = "/CategoryAddServlet")
@MultipartConfig
public class CategoryAddServlet extends HttpServlet {
    //添加分类
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        //获得前端category_add.jsp传过来的值
        String categoryName = request.getParameter("categoryName");
        Part img = request.getPart("categoryIcon");
        //获得上传的路径
        ServletContext servletContext = getServletContext();
        //获得真实路径
        String realPath = servletContext.getRealPath("/upload");
        //获得请求头
        String header = img.getHeader("Content-Disposition");
        int statr = header.lastIndexOf(".");
        int end = header.lastIndexOf("\"");
        //截取后缀名
        String substring = header.substring(statr, end);
        //拼接
        String fileName = UUID.randomUUID().toString().replace("-", "") + substring;
        //上传路径
        String filePath = realPath + "\\" + fileName;
        //响应
        img.write(filePath);

        //添加到数据库
        CategoryService service = new CategoryServiceImpl();
        service.insertCategory(categoryName, "upload/" + fileName);


        //查询全部分类表
        List<Category> all = service.findAll();
        request.setAttribute("categoryList", all);
        //转发
        request.getRequestDispatcher("category_list.jsp").forward(request, response);


    }
}
