package com.jobportal.servlet;

import com.jobportal.dao.JobDAO;
import com.jobportal.model.User;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/post-job")
public class PostJobServlet extends HttpServlet {
 protected void doPost(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{
  User user=(User)req.getSession().getAttribute("user");
  if(user==null){resp.sendRedirect(req.getContextPath()+"/login.jsp");return;}
  if(!user.isRecruiter()){resp.sendError(403,"Recruiter access required.");return;}
  req.setCharacterEncoding("UTF-8");
  String title=val(req,"title"),company=val(req,"company"),location=val(req,"location"),type=val(req,"jobType"),
         salary=val(req,"salaryRange"),description=val(req,"description"),skills=val(req,"requiredSkills");
  if(title.isEmpty()||company.isEmpty()||location.isEmpty()||description.isEmpty()){
   req.setAttribute("error","Title, company, location and description are required.");
   req.getRequestDispatcher("/post-job.jsp").forward(req,resp);return;
  }
  if(!type.matches("FULL_TIME|PART_TIME|INTERNSHIP|CONTRACT"))type="FULL_TIME";
  try{new JobDAO().create(user.getId(),title,company,location,type,salary,description,skills);
       resp.sendRedirect(req.getContextPath()+"/dashboard?posted=1");}
  catch(SQLException e){throw new ServletException("Could not post job.",e);}
 }
 private String val(HttpServletRequest r,String k){String v=r.getParameter(k);return v==null?"":v.trim();}
}
