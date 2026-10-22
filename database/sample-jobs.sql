USE job_portal;
-- Replace recruiter@example.com with the email of a registered account whose role is RECRUITER.
INSERT INTO jobs (recruiter_id,title,company,location,job_type,salary_range,description,required_skills)
SELECT id,'Java Full Stack Developer','BrightPath Technologies','Bengaluru','FULL_TIME','₹4–6 LPA',
'Build and maintain Java web applications. Work with the team on features, bug fixes, and database-backed pages.',
'Core Java, JSP, Servlets, JDBC, MySQL, HTML, CSS'
FROM users WHERE email='recruiter@example.com' LIMIT 1;

INSERT INTO jobs (recruiter_id,title,company,location,job_type,salary_range,description,required_skills)
SELECT id,'Software Developer Intern','Northstar Digital','Hybrid','INTERNSHIP','₹15,000–₹25,000/month',
'Support application development, test features, and learn from experienced developers.',
'Java, SQL, Git, JavaScript'
FROM users WHERE email='recruiter@example.com' LIMIT 1;

INSERT INTO jobs (recruiter_id,title,company,location,job_type,salary_range,description,required_skills)
SELECT id,'Junior Backend Developer','CloudHarbor Systems','Remote','FULL_TIME','₹3.5–5 LPA',
'Help implement backend features, write SQL queries, and maintain reliable services.',
'Java, JDBC, MySQL, REST basics'
FROM users WHERE email='recruiter@example.com' LIMIT 1;
