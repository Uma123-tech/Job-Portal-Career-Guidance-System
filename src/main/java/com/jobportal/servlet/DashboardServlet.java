package com.jobportal.servlet;

import com.jobportal.dao.ApplicationDAO;
import com.jobportal.dao.JobDAO;
import com.jobportal.model.User;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {
 protected void doGet(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{
  User user=(User)req.getSession().getAttribute("user");
  if(user==null){resp.sendRedirect(req.getContextPath()+"/login.jsp");return;}
  try{
   if(user.isRecruiter()){
    req.setAttribute("myJobs",new JobDAO().byRecruiter(user.getId()));
    req.setAttribute("applicants",new ApplicationDAO().applicantsForRecruiter(user.getId()));
    req.getRequestDispatcher("/recruiter-dashboard.jsp").forward(req,resp);
   }else{
    req.setAttribute("applications",new ApplicationDAO().forSeeker(user.getId()));
    req.getRequestDispatcher("/seeker-dashboard.jsp").forward(req,resp);
   }
  }catch(SQLException e){throw new ServletException("Could not load dashboard.",e);}
 }
}
