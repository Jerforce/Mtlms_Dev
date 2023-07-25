package com.qf.servlet;

import com.google.gson.Gson;
import com.qf.vo.ResultVo;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

@WebServlet(name = "ImageUploadServlet", value = "/ImageUploadServlet")
@MultipartConfig
public class ImageUploadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    //图片回显
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获得图片的上传的路径目录upload
        ServletContext servletContext = getServletContext();
        String realPath = servletContext.getRealPath("/upload");
        //2.获得图片的后缀名
        Part img = request.getPart("brandLogoImg");
        String header = img.getHeader("Content-Disposition");
        //3.重命名+拼接
        int statr = header.lastIndexOf(".");
        int end = header.lastIndexOf("\"");
        String ext = header.substring(statr, end);
        //拼接
        String fileName = UUID.randomUUID().toString().replace("-", "") + ext;
        String filePath = realPath + "\\" + fileName;
        //4.写入
        img.write(filePath);
        //5.响应图片
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        String jsonStr = new Gson().toJson(new ResultVo(1000, "upload/" + fileName));
        out.println(jsonStr);
        out.flush();
        out.close();
    }
}
