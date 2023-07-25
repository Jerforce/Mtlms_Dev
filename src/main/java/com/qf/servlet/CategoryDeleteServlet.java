package com.qf.servlet;

import com.google.gson.Gson;
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

@WebServlet(name = "CategoryDeleteServlet", value = "/CategoryDeleteServlet")
public class CategoryDeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //从前端传过来的值category_list.jsp  jaxa里面的category_del()
        String cid = request.getParameter("cid");
        int i = Integer.parseInt(cid);
        //删除一个一级分类以id去删除
        CategoryService service= new CategoryServiceImpl();
        int i1 = service.deleteCategoryById(i);
        //判断删除的是否大于0
        ResultVo resultVo = i1 > 0 ? new ResultVo(1000, "success") : new ResultVo(1001, "fail");
        //把对象转gson格式
        String json = new Gson().toJson(resultVo);
        //响应给前端category_del()
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println(json);
        out.flush();
        out.close();
    }
}
