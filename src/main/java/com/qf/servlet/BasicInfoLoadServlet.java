package com.qf.servlet; /**
 * @author Jerforce
 * @date 2023/7/20 星期四 16:50:01
 */

import com.google.gson.Gson;
import com.qf.entity.BasicInfo;
import com.qf.service.BasicInfoService;
import com.qf.service.impl.BasicInfoServiceImpl;
import com.qf.vo.ResultVo;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "BasicInfoLoadServlet", value = "/BasicInfoLoadServlet")
public class BasicInfoLoadServlet extends HttpServlet {
    BasicInfoService basicInfoService = new BasicInfoServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.查询评估类目信息
        List<BasicInfo> basicInfoList = basicInfoService.findAll();

        //2.响应ajax请求，将basicInfoList转换成JSON格式响应给页面的ajax请求
        ResultVo vo =  basicInfoList!=null ? new ResultVo(1000,"success",basicInfoList):
                new ResultVo(1001,"fail");

        // 查询成功： {code:1000, msg:success, data:[{},{},{}]}
        // 查询失败： {code:1001, msg:fail, data:null}
        String jsonStr = new Gson().toJson(vo);

        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print(jsonStr);
        out.flush();
        out.close();

    }
}
