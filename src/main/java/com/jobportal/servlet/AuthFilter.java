package com.jobportal.servlet;

import com.jobportal.model.User;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;

@WebFilter(urlPatterns={"/dashboard","/post-job","/apply"})
public class AuthFilter implements Filter {
 public void doFilter(ServletRequest request,ServletResponse response,FilterChain chain)throws IOException,ServletException{
  HttpServletRequest req=(HttpServletRequest)request;HttpServletResponse resp=(HttpServletResponse)response;
  HttpSession session=req.getSession(false);User user=session==null?null:(User)session.getAttribute("user");
  if(user==null){resp.sendRedirect(req.getContextPath()+"/login.jsp");return;}
  chain.doFilter(request,response);
 }
}
