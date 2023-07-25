package com.qf.servlet;

import com.google.gson.Gson;
import com.qf.entity.Brand;
import com.qf.service.BrandService;
import com.qf.service.impl.BrandServiceImpl;
import com.qf.vo.ResultVo;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "BrandListForAjaxServlet", value = "/BrandListForAjaxServlet")
public class BrandListForAjaxServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
    //-- 根据产品id去查全部的一级菜单的内容
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获得brand全部的数据-->Cid
        //封装为json格式然后在进行响应
        int cId = Integer.parseInt(request.getParameter("categoryId"));
        //-- 根据产品分类查品牌
        BrandService service=new BrandServiceImpl();
        List<Brand> brandList = service.findByCId(cId);
        //封装成json格式
        ResultVo resultVo = brandList != null ? new ResultVo(1000, "success", brandList) : new ResultVo(1001, "fail");
        //响应
        String jsonStr = new Gson().toJson(resultVo);
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println(jsonStr);
        out.flush();
        out.close();

    }
}
