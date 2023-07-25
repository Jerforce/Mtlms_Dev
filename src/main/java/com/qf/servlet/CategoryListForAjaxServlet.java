package com.qf.servlet;

import com.google.gson.Gson;
import com.qf.entity.Category;
import com.qf.service.CategoryService;
import com.qf.service.impl.CategoryServiceImpl;
import com.qf.vo.ResultVo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "CategoryListForAjaxServlet", value = "/CategoryListForAjaxServlet")
public class CategoryListForAjaxServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    //查询全部的产品分类表，resultVo转成jsonStr格式
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //通过ajax的请求
        //查询出全一级分类 集合
        CategoryService service = new CategoryServiceImpl();
        //查询全部的查询产品分类表
        List<Category> categoryList = service.findAll();
        //将数据库集合转成json格式的字符串
        ResultVo resultVo = categoryList != null ? new ResultVo(1000, "success",categoryList) : new ResultVo(1001, "fail");
        //将数据集合转成json格式的字符串
        String jsonStr = new Gson().toJson(resultVo);
        //响应
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println(jsonStr);
        out.flush();
        out.close();
    }
}
