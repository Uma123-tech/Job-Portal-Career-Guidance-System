package com.jobportal.servlet;

import com.jobportal.dao.JobDAO;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/jobs")
public class JobListServlet extends HttpServlet {
 protected void doGet(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{
  String q=req.getParameter("q"),location=req.getParameter("location");
  q=q==null?"":q.trim();location=location==null?"":location.trim();
  try{req.setAttribute("jobs",new JobDAO().search(q,location));req.setAttribute("q",q);req.setAttribute("location",location);
      req.getRequestDispatcher("/jobs.jsp").forward(req,resp);}
  catch(SQLException e){throw new ServletException("Could not load jobs. Check database connection.",e);}
 }
}
