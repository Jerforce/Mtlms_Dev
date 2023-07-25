package com.qf.servlet; /**
 * @author Jerforce
 * @date 2023/7/20 星期四 16:24:41
 */

import com.google.gson.Gson;
import com.qf.entity.InfoDetail;
import com.qf.service.InfoDetailService;
import com.qf.service.impl.InfoDetailServiceImpl;
import com.qf.vo.ResultVo;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "InfoDetailAddServlet", value = "/InfoDetailAddServlet")
public class InfoDetailAddServlet extends HttpServlet {
    InfoDetailService infoDetailService = new InfoDetailServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.接收ajax请求传递过来的参数
        String infoDetailName = request.getParameter("infoDetailName");
        String infoDetailDesc = request.getParameter("infoDetailDesc");
        int basicInfoId = Integer.parseInt( request.getParameter("basicInfoId") );
        InfoDetail infoDetail = new InfoDetail(0,infoDetailName,infoDetailDesc);
        //2.调用InfoDetailService保存评估选项信息
        boolean result = infoDetailService.saveInfoDetail(infoDetail,basicInfoId);

        //3.响应ajax请求
        ResultVo vo = result?new ResultVo(1000,"success"):new ResultVo(1001,"fail");
        String jsonStr = new Gson().toJson(vo);
        //设置响应编码
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        //获取输出流
        PrintWriter out = response.getWriter();
        out.print(jsonStr);
        out.flush();
        out.close();


    }
}
