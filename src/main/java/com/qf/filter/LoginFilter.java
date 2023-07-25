package com.qf.filter;

import com.qf.entity.Manager;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebFilter("/*")
public class LoginFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        //判断受限资源和非受限资源
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        //响应地址
        String url = req.getRequestURL().toString();
//        System.out.println("LoginFilter方法内" + url);
        String path = url.substring(url.lastIndexOf("/") + 1);
//        System.out.println("LoginFilter方法内" + path);
        //判断path地址的后缀
        if("login.jsp".equals(path) || "ManagerLoginServlet".equals(path) || "CheckCodeServlet".equals(path) || path.endsWith(".js")
                || path.endsWith(".css") || path.endsWith(".jpg") || path.endsWith(".png") || path.endsWith(".bmp")){
            //放行
            chain.doFilter(req, resp);
        } else {
            Manager mgr = (Manager) req.getSession().getAttribute("mgr");
            //ManagerLoginServlet调用查找是否有这个人然后把对象存到Session中然后判断里面对象是否为空
            if (mgr != null) {
                //放行
                chain.doFilter(req, resp);
            } else {
                req.setAttribute("tips", "请先登录！");
                //【转发】
                req.getRequestDispatcher("login.jsp").forward(req, resp);
            }
        }

//        System.out.println("============================================================================");
    }

}
