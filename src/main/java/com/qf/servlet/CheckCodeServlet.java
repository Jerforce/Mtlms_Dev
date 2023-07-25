package com.qf.servlet;

import javax.imageio.ImageIO;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

//登录验证码模块
@WebServlet(name = "CheckCodeServlet", value = "/CheckCodeServlet")
public class CheckCodeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置画布的宽高
        int width = 200;
        int height = 70;
        //生成画布:图片缓冲区
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        //创建画笔
        Graphics2D pen = image.createGraphics();
        //获得一个随机的颜色
        pen.setColor(getColor());
        //填充背景颜色
        pen.fillRect(0, 0, width, height);
        //绘制字母
        int letterNum = 4;//绘制字母的个数
        int space = 20;//间隔
        int letterWidth = (width - (letterNum + 1) * space) / letterNum;//每个字母的宽度
        //循环绘制字母
        Random random = new Random();
        String vCode = "";
        for (int i = 0; i < letterNum; i++) {
            int ascii = random.nextInt(26) + 97;
            String letter = Character.toString((char) ascii);//随机生成的字母
            pen.setColor(getColor());//设置颜色
            pen.setFont(new Font("宋体", Font.BOLD, 70));//设置字体:字体，加粗，字号
            pen.drawString(letter, space + (space + letterWidth) * i, height - space);//绘制字母
            vCode += letter;
        }
        //将vCode放进session
        request.getSession().setAttribute("vCode", vCode);
        //写入到图片
        ImageIO.write(image, "png", response.getOutputStream());
    }

    //获取随机颜色的方法
    private Color getColor() {
        Random random = new Random();
        int red = random.nextInt(256);//取值0-255
        int green = random.nextInt(256);
        int blue = random.nextInt(256);
        Color color = new Color(red, green, blue);//随机生成的rgb颜色对象
        return color;
    }

}
