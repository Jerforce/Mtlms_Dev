package com.qf.servlet; /**
 * @author Jerforce
 * @date 2023/7/19 星期三 18:32:56
 */

import com.qf.entity.Brand;
import com.qf.entity.Category;
import com.qf.service.BrandService;
import com.qf.service.CategoryService;
import com.qf.service.impl.BrandServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet(name = "BrandUpdateServlet", value = "/BrandUpdateServlet")
public class BrandUpdateServlet extends HttpServlet {
    CategoryService categoryService = new CategoryServiceImpl();
    BrandService brandService = new BrandServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.接收修改页面修改后的品牌信息
        request.setCharacterEncoding("utf-8");
        int brandId = Integer.parseInt(request.getParameter("brandId"));  //隐藏域
        String brandName = request.getParameter("brandName");
        String brandLogoPath = request.getParameter("brandLogoPath");
        String brandDesc = request.getParameter("brandDesc");
        int brandStatus = Integer.parseInt(request.getParameter("brandStatus"));
        Brand brand = new Brand(brandId, brandName, brandLogoPath, brandDesc, new Date(), brandStatus);

        //2.执行修改
        boolean b = brandService.updateBrand(brand);
        String tips = b ? "<label style='color:green'>品牌修改成功！</label>" :
                "<label style='color:red'>品牌修改失败！</label>";

        //3.跳转到 brand_list.jsp
        //a.查询分类列表
        List<Category> categoryList = categoryService.findAll();
        //b.第一个分类ID
        int cid = categoryList.get(0).getCategoryId();
        //c.第一个分类ID下的品牌列表
        List<Brand> brandList = brandService.findByCId(cid);

        request.setAttribute("tips", tips);
        request.setAttribute("categoryList", categoryList);
        request.setAttribute("cid", cid);
        request.setAttribute("brandList", brandList);
        request.getRequestDispatcher("brand_list.jsp").forward(request, response);
    }
}

