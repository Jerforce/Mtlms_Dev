package com.qf.servlet; /**
 * @author Jerforce
 * @date 2023/7/19 星期三 14:44:01
 */

import com.qf.entity.Good;
import com.qf.service.GoodService;
import com.qf.service.impl.GoodServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "GoodsAddServlet", value = "/GoodsAddServlet")
public class GoodsAddServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.添加商品，返回商品id
        String goodsName = request.getParameter("goodsName");
        String goodsImg = request.getParameter("goodsImgPath");
        int goodsCost = Integer.parseInt(request.getParameter("goodsCost"));
        int goodsMinPrice = Integer.parseInt(request.getParameter("goodsMinPrice"));
        GoodService goodService = new GoodServiceImpl();
        //此时返回的就是商品id
        int goodId = goodService.insertGood(new Good(0,goodsName,goodsImg,goodsCost,goodsMinPrice));
        //2.添加品牌-商品关联信息
        int brandId = Integer.parseInt(request.getParameter("brandId"));
        goodService.insertBrandAndGood(brandId,goodId);//执行添加
        //3.添加商品-评估关联信息
        //获得多个选中的复选框的值
        String[] infoDetailIds = request.getParameterValues("infoDetailId");
        for (String infoDetailId : infoDetailIds) {
            int detailId = Integer.parseInt(infoDetailId);
            int discount = Integer.parseInt(request.getParameter("price_"+infoDetailId));
            goodService.insertGoodAndDetail(goodId,detailId,discount);
        }
        //4.页面跳转
        request.setAttribute("tips","添加商品成功！");
        request.getRequestDispatcher("products_list.jsp").forward(request,response);
    }
}
