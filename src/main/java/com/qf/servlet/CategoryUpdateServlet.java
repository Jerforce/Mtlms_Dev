package com.qf.servlet;

import com.qf.service.CategoryService;
import com.qf.service.impl.CategoryServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.UUID;

//???????????????????????
@MultipartConfig
@WebServlet(name = "CategoryUpdateServlet", value = "/CategoryUpdateServlet")
public class CategoryUpdateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        //1.获得图片的上传的路径目录upload
        ServletContext servletContext = getServletContext();
        //获得"/upload"的地址真实路径
        String realPath = servletContext.getRealPath("/upload");
        //获得前端category_modify.jsp传过来的值
        String categoryId = request.getParameter("categoryId");
        int id = Integer.parseInt(categoryId);
        String categoryName = request.getParameter("categoryName");
        //获得图片信息
        Part img = request.getPart("categoryIcon");
        //状态
        String status = request.getParameter("status");
        String header = img.getHeader("Content-Disposition");
        System.out.println("=====================");
        System.out.println(header);
        //截取内容
        int start = header.lastIndexOf(".");
        System.out.println(start);
        int end = header.lastIndexOf("\"");
        System.out.println(end);
/*        String tips = ""; // 初始化提示信息

        String substring = null;
        try {

            // 添加异常处理
            if (start >= 0 && end > start) {
                substring = header.substring(start, end);
                //文件名sdsd54d5sda4.jsp
                String fileName = UUID.randomUUID().toString().replace("-", "") + substring;
                String filePath = realPath + "\\" + fileName;

                // 执行分类修改的操作，此处可能抛出其他异常
                // Your code to handle category update goes here...

                tips = "<label style='color:green'>修改分类成功！</label>";
            } else {
                tips = "<label style='color:red'>修改分类失败！无效的文件名或文件类型！</label>";
            }
        } catch (Exception e) {
            // 捕获其他异常并提供错误提示
            tips = "<label style='color:red'>修改分类失败！发生异常：" + e.getMessage() + "</label>";
        }*/




       //拼接地址
        String substring = header.substring(start, end);
        //文件名sdsd54d5sda4.jsp
        String fileName = UUID.randomUUID().toString().replace("-", "") + substring;
        String filePath = realPath + "\\" + fileName;
        //写入
        img.write(filePath);
        CategoryService service = new CategoryServiceImpl();
        service.modifyCategoryNameAndImg(categoryName, "upload/" + fileName, id, status);

        request.getRequestDispatcher("CategoryListServlet").forward(request, response);

    }
}
