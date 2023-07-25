package com.qf.servlet; /**
 * @author Jerforce
 * @date 2023/7/19 星期三 18:30:14
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
import java.util.List;

@WebServlet(name = "BrandListServlet", value = "/BrandListServlet")
public class BrandListServlet extends HttpServlet {
    CategoryService categoryService = new CategoryServiceImpl();
    BrandService brandService = new BrandServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String categoryId = request.getParameter("categoryId");
        //2.查询所有的分类列表
        List<Category> categoryList = categoryService.findAll();
        //3.根据分类ID，查询当前分类下的品牌信息，如果categoryId==null,则默认查询分类列表中第一个分类下的品牌
        int cid = categoryId==null ? categoryList.get(0).getCategoryId() : Integer.parseInt( categoryId );
        List<Brand> brandList = brandService.findByCId(cid);
        //4. 将分类列表、当前显示的分类ID、分类ID下的品牌列表传递到 brand_list.jsp
        request.setAttribute("categoryList",categoryList);
        request.setAttribute("cid",cid);
        request.setAttribute("brandList",brandList);
        request.getRequestDispatcher("brand_list.jsp").forward(request,response);
    }
}
