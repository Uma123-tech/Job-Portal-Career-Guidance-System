package com.jobportal.servlet;

import com.jobportal.dao.UserDAO;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{
        req.setCharacterEncoding("UTF-8");
        String name=trim(req.getParameter("fullName")), email=trim(req.getParameter("email")).toLowerCase();
        String password=req.getParameter("password");
        if(name.isEmpty()||email.isEmpty()||password==null||password.length()<8){
            req.setAttribute("error","Enter your name and email. Password must be at least 8 characters.");
            req.getRequestDispatcher("/register.jsp").forward(req,resp);return;
        }
        try{
            new UserDAO().register(name,email,password);
            resp.sendRedirect(req.getContextPath()+"/login.jsp?registered=1");
        }catch(SQLException e){
            req.setAttribute("error",e.getErrorCode()==1062?"An account with this email already exists.":"Could not register. Check database configuration.");
            req.getRequestDispatcher("/register.jsp").forward(req,resp);
        }
    }
    private String trim(String s){return s==null?"":s.trim();}
}
