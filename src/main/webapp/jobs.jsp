<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*,com.jobportal.model.Job,com.jobportal.model.User" %>
<!DOCTYPE html><html><head><meta charset="UTF-8"><meta name="viewport" content="width=device-width,initial-scale=1"><title>Find jobs | CareerLaunch</title><link rel="stylesheet" href="<%=request.getContextPath()%>/assets/style.css"></head><body>
<%@ include file="includes/header.jspf" %>
<section class="hero"><div class="hero-copy"><p class="eyebrow">YOUR NEXT CHAPTER STARTS HERE</p><h1>Find work that<br><span>moves you forward.</span></h1><p>Discover opportunities, build your skills, and take the next step in your career.</p></div><div class="hero-stat"><strong>01</strong><span>Search. Apply. Grow.</span></div></section>
<main class="container">
 <form class="searchbar" action="<%=request.getContextPath()%>/jobs" method="get">
  <label>Role, company or skill<input name="q" placeholder="e.g. Java Developer" value="<%=esc((String)request.getAttribute("q"))%>"></label>
  <label>Location<input name="location" placeholder="e.g. Bengaluru" value="<%=esc((String)request.getAttribute("location"))%>"></label>
  <button class="btn" type="submit">Search jobs</button>
 </form>
 <div class="section-heading"><div><p class="eyebrow">OPPORTUNITIES</p><h2>Latest openings</h2></div><span class="muted"><%=((List<Job>)request.getAttribute("jobs")).size()%> jobs found</span></div>
 <div class="job-grid">
 <% List<Job> jobs=(List<Job>)request.getAttribute("jobs"); if(jobs.isEmpty()){ %><div class="empty-state">No jobs match your search yet. Try another keyword or location.</div><% } for(Job j:jobs){ %>
 <article class="job-card"><div class="company-mark"><%=esc(j.getCompany().substring(0,1).toUpperCase())%></div><div class="job-meta"><span class="tag"><%=esc(j.getJobType().replace('_',' '))%></span><span class="muted"><%=j.getCreatedAt()==null?"":j.getCreatedAt().toLocalDateTime().toLocalDate()%></span></div><h3><%=esc(j.getTitle())%></h3><p class="company"><%=esc(j.getCompany())%></p><p class="muted">⌖ <%=esc(j.getLocation())%></p><%if(j.getSalaryRange()!=null&&!j.getSalaryRange().isEmpty()){%><p class="salary"><%=esc(j.getSalaryRange())%></p><%}%><p class="description"><%=esc(j.getDescription())%></p><%if(j.getRequiredSkills()!=null&&!j.getRequiredSkills().isEmpty()){%><p class="skills"><%=esc(j.getRequiredSkills())%></p><%}%>
 <% if(currentUser!=null && !currentUser.isRecruiter()){ %><form method="post" action="<%=request.getContextPath()%>/apply"><input type="hidden" name="jobId" value="<%=j.getId()%>"><button class="btn btn-block" type="submit">Apply now →</button></form><% }else if(currentUser==null){ %><a class="btn btn-block" href="<%=request.getContextPath()%>/login.jsp">Log in to apply →</a><% } %>
 </article><% } %>
 </div>
</main><footer>CareerLaunch · Built with Java, JSP, Servlets &amp; MySQL</footer>
</body></html>
<%!
private String esc(String s){if(s==null)return "";return s.replace("&","&amp;").replace("<","&lt;").replace(">","&gt;").replace("\"","&quot;").replace("'","&#39;");}
%>
