package com.jobportal.servlet;

import com.jobportal.dao.UserDAO;
import com.jobportal.model.User;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{
        req.setCharacterEncoding("UTF-8");
        try{
            User user=new UserDAO().login(req.getParameter("email")==null?"":req.getParameter("email").trim().toLowerCase(),
                                         req.getParameter("password")==null?"":req.getParameter("password"));
            if(user==null){req.setAttribute("error","Invalid email or password.");req.getRequestDispatcher("/login.jsp").forward(req,resp);return;}
            HttpSession old=req.getSession(false);if(old!=null)old.invalidate();
            HttpSession session=req.getSession(true);session.setAttribute("user",user);session.setMaxInactiveInterval(30*60);
            resp.sendRedirect(req.getContextPath()+"/jobs");
        }catch(SQLException e){req.setAttribute("error","Could not log in. Check database configuration.");req.getRequestDispatcher("/login.jsp").forward(req,resp);}
    }
}
