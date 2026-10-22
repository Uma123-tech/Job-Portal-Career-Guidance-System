package com.jobportal.servlet;

import com.jobportal.dao.ApplicationDAO;
import com.jobportal.dao.JobDAO;
import com.jobportal.model.User;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/apply")
public class ApplyServlet extends HttpServlet {
 protected void doPost(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{
  User user=(User)req.getSession().getAttribute("user");
  if(user==null){resp.sendRedirect(req.getContextPath()+"/login.jsp");return;}
  if(user.isRecruiter()){resp.sendError(403,"Recruiter accounts cannot apply for jobs.");return;}
  try{
   int jobId=Integer.parseInt(req.getParameter("jobId"));
   if(new JobDAO().findById(jobId)==null){resp.sendError(404);return;}
   new ApplicationDAO().apply(jobId,user.getId());
   resp.sendRedirect(req.getContextPath()+"/dashboard?applied=1");
  }catch(NumberFormatException e){resp.sendError(400,"Invalid job ID.");}
   catch(SQLException e){
    if(e.getErrorCode()==1062){resp.sendRedirect(req.getContextPath()+"/dashboard?duplicate=1");}
    else throw new ServletException("Could not submit application.",e);
  }
 }
}
