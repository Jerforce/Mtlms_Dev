package com.qf.servlet; /**
 * @author Jerforce
 * @date 2023/7/20 星期四 17:30:16
 */

import com.qf.service.BasicInfoService;
import com.qf.service.impl.BasicInfoServiceImpl;
import com.qf.vo.ResultVo;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "BasicInfoDeleteServlet", value = "/BasicInfoDeleteServlet")
public class BasicInfoDeleteServlet extends HttpServlet {
    BasicInfoService service=new BasicInfoServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int basicInfoId=Integer.parseInt(request.getParameter("bid"));
        boolean b=service.deleteBasicInfoById(basicInfoId);

        ResultVo resultVo=b?new ResultVo(1000,"success"):new ResultVo(1001,"fail");
        String jsonStr=new com.google.gson.Gson().toJson(resultVo);
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out=response.getWriter();
        out.print(jsonStr);
        out.flush();
        out.close();

    }
}
