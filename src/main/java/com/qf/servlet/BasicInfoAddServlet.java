package com.qf.servlet; /**
 * @author Jerforce
 * @date 2023/7/20 星期四 16:33:04
 */

import com.qf.dao.BasicInfoDao;
import com.qf.entity.BasicInfo;
import com.qf.service.BasicInfoService;
import com.qf.service.impl.BasicInfoServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "BasicInfoAddServlet", value = "/BasicInfoAddServlet")
public class BasicInfoAddServlet extends HttpServlet {
    BasicInfoService basicInfoService = new BasicInfoServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding( "utf-8" );
        //1.接收ajax请求传递过来的参数
        String basicInfoName = request.getParameter("basicInfoName");
        BasicInfo basicInfo = new BasicInfo(0,basicInfoName,1);

        //2.保存类目信息
        boolean b = basicInfoService.saveBasicInfo(basicInfo);
        //3.查询所有评估类目列表、跳转到‘basic_info_list.jsp’
        //a.添加操作的提示信息

        String tips = b?"<label style='color:green'>添加评估类目成功！</label>":
                "<label style='color:red'>添加评估类目失败！</label>";
        request.setAttribute("tips",tips);
        //b.类目列表
        List<BasicInfo> basicInfoList = basicInfoService.findAll();
        request.setAttribute("basicInfoList",basicInfoList);
        //c.跳转到‘basic_info_list.jsp’
        request.getRequestDispatcher("basic_info_list.jsp").forward(request,response);
        //
    }
}
