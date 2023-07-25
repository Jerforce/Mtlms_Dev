package com.qf.servlet; /**
 * @author Jerforce
 * @date 2023/7/19 星期三 18:27:57
 */

import com.google.gson.Gson;
import com.qf.service.BrandService;
import com.qf.service.CategoryService;
import com.qf.service.impl.BrandServiceImpl;
import com.qf.vo.ResultVo;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "BrandDeleteServlet", value = "/BrandDeleteServlet")
public class BrandDeleteServlet extends HttpServlet {
    CategoryService categoryService = new CategoryServiceImpl();
    BrandService brandService = new BrandServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取brandId执行删除
        int brandId = Integer.parseInt( request.getParameter("brandId") );
        boolean b = brandService.deleteBrandById(brandId);

        //2.响应ajax请求

        ResultVo vo = b? new ResultVo(1000, "success"):new ResultVo(1001, "fail");
        String jsonStr = new Gson().toJson(vo);

        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print(jsonStr);
        out.flush();
        out.close();
    }
}
