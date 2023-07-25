package com.qf.servlet;

import com.google.gson.Gson;
import com.qf.entity.Good;
import com.qf.service.impl.GoodServiceImpl;
import com.qf.vo.ResultVo;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "GoodsListForAjaxServlet", value = "/GoodsListForAjaxServlet")
public class GoodsListForAjaxServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        //1.获得品牌id
        int brandId = Integer.parseInt(request.getParameter("brandId"));
        //2.根据品牌id查询具体的商品信息
        List<Good> goods = new GoodServiceImpl().findByBId(brandId);
        //3.将商品信息转发为json字符串
        ResultVo resultVo = goods != null ? new ResultVo(1000, "success", goods) : new ResultVo(1001, "fail");
        String json = new Gson().toJson(resultVo);
        //4.响应
        PrintWriter out = response.getWriter();
        response.setContentType("application/json;charset=utf-8");
        out.println(json);
        out.flush();
        out.close();
    }
}
