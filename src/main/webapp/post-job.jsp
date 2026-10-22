<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.jobportal.model.User" %>
<% User u=(User)session.getAttribute("user");if(u==null){response.sendRedirect(request.getContextPath()+"/login.jsp");return;}if(!u.isRecruiter()){response.sendError(403,"Recruiter access required.");return;} %>
<!DOCTYPE html><html><head><meta charset="UTF-8"><meta name="viewport" content="width=device-width,initial-scale=1"><title>Post a job | CareerLaunch</title><link rel="stylesheet" href="<%=request.getContextPath()%>/assets/style.css"></head><body><%@ include file="includes/header.jspf" %>
<main class="auth-wrap wide"><section class="auth-card"><p class="eyebrow">FIND YOUR NEXT TEAMMATE</p><h1>Post a new role.</h1><%if(request.getAttribute("error")!=null){%><div class="alert"><%=request.getAttribute("error")%></div><%}%>
<form method="post" action="<%=request.getContextPath()%>/post-job" class="stack-form">
<label>Job title<input name="title" required maxlength="150" placeholder="Java Full Stack Developer"></label>
<label>Company<input name="company" required maxlength="150"></label>
<label>Location<input name="location" required maxlength="120" placeholder="Bengaluru / Remote"></label>
<label>Job type<select name="jobType"><option value="FULL_TIME">Full time</option><option value="PART_TIME">Part time</option><option value="INTERNSHIP">Internship</option><option value="CONTRACT">Contract</option></select></label>
<label>Salary range (optional)<input name="salaryRange" maxlength="100" placeholder="₹4–6 LPA"></label>
<label>Required skills<input name="requiredSkills" maxlength="500" placeholder="Java, JDBC, MySQL, HTML, CSS"></label>
<label>Job description<textarea name="description" required rows="5" maxlength="5000"></textarea></label>
<button class="btn btn-block">Publish job</button></form></section></main></body></html>