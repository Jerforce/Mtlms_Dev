package com.qf.servlet; /**
 * @author Jerforce
 * @date 2023/7/20 星期四 16:48:20
 */

import com.qf.entity.BasicInfo;
import com.qf.entity.InfoDetail;
import com.qf.service.BasicInfoService;
import com.qf.service.InfoDetailService;
import com.qf.service.impl.BasicInfoServiceImpl;
import com.qf.service.impl.InfoDetailServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "BasicInfoAndDetailListServlet", value = "/BasicInfoAndDetailListServlet")
public class BasicInfoAndDetailListServlet extends HttpServlet {
    BasicInfoService basicInfoService = new BasicInfoServiceImpl();
    InfoDetailService infoDetailService = new InfoDetailServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.查询所有评估类目列表
        List<BasicInfo> basicInfoList = basicInfoService.findAll();
         //2.遍历所有类目
               for (int i = 0; i < basicInfoList.size() ; i++) {
                   BasicInfo basicInfo = basicInfoList.get(i);
                   //根据评估类目的ID查询此评估类目下的选项
                   List<InfoDetail> infoDetailList = infoDetailService.ListInfoDetailsByBasicInfo(basicInfo.getBasicInfoId());
                   //将此评估类目下的选项存储到basicInfo对象中
                   basicInfo.setInfoDetailList(infoDetailList);
               }

               //3.将评估类目集合传递到商品添加页面  products_add.jsp
               request.setAttribute("basicInfoList",basicInfoList);
               request.getRequestDispatcher("products_add.jsp").forward(request,response);
    }
}
