package com.qf.servlet; /**
 * @author Jerforce
 * @date 2023/7/19 星期三 18:31:31
 */

import com.qf.entity.Brand;
import com.qf.service.BrandService;
import com.qf.service.CategoryService;
import com.qf.service.impl.BrandServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "BrandQueryServlet", value = "/BrandQueryServlet")
public class BrandQueryServlet extends HttpServlet {
    CategoryService categoryService = new CategoryServiceImpl();
    BrandService brandService = new BrandServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.接收品牌ID
        int brandId = Integer.parseInt( request.getParameter("brandId") );
        //2.根据ID查询品牌信息（如果支持修改分类的话，也需要查询对应的分类列表）
        Brand brand = brandService.getBrandById(brandId);
        //3.传递到修改页面
        request.setAttribute("brand",brand);
        request.getRequestDispatcher("brand_modify.jsp").forward(request,response);
    }
}
