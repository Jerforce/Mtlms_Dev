package com.qf.servlet; /**
 * @author Jerforce
 * @date 2023/7/19 星期三 15:22:36
 */

import com.google.gson.Gson;
import com.qf.service.impl.GoodServiceImpl;
import com.qf.vo.ResultVo;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "DelGoodAllByIdServlet", value = "/DelGoodAllByIdServlet")
public class DelGoodAllByIdServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获得传入的id数组
        String goodIds = request.getParameter("goodIds");
        String[]ids = goodIds.split(",");
        for (String id : ids) {
            //调用service层方法，根据当前id进行删除

            System.out.println(id);
        }
        //删除完毕响应
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        ResultVo resultVo = new ResultVo(1000,"删除成功！");
        String jsonStr = gson.toJson(resultVo);
        out.println(jsonStr);
        out.flush();
        out.close();
    }
}
