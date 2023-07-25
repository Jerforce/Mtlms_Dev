package com.qf.servlet; /**
 * @author Jerforce
 * @date 2023/7/19 星期三 18:25:29
 */

import com.qf.entity.Brand;
import com.qf.entity.Category;
import com.qf.service.BrandService;
import com.qf.service.CategoryService;
import com.qf.service.impl.BrandServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet(name = "BrandAddServlet", value = "/BrandAddServlet")

public class BrandAddServlet extends HttpServlet {
    CategoryService categoryService = new CategoryServiceImpl();
    BrandService brandService = new BrandServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.接收品牌信息
        String brandName = request.getParameter("brandName");
        String brandLogoPath = request.getParameter("brandLogoPath");
        String brandDesc = request.getParameter("brandDesc");
        int brandStatus = Integer.parseInt( request.getParameter("brandStatus") );
        Brand brand = new Brand(0, brandName, brandLogoPath, brandDesc, new Date(), brandStatus);
        //2.接收选择的分类ID
        int categoryId = Integer.parseInt( request.getParameter("categoryId") );
        //3.执行保存
        boolean b = brandService.addBrand(brand, categoryId);
        String tips = b?"<label style='color:green'>品牌添加成功！</label>":
                "<label style='color:red'>品牌添加失败！</label>";

        //4.跳转到 brand_list.jsp
        List<Category>categoryList = categoryService.findAll();
        int cid = categoryList.get(0).getCategoryId() ;
        List<Brand> brandList = brandService.findByCId(cid);

        request.setAttribute("tips",tips);
        request.setAttribute("categoryList",categoryList);
        request.setAttribute("cid",cid);
        request.setAttribute("brandList",brandList);
        request.getRequestDispatcher("brand_list.jsp").forward(request,response);
    }
}
