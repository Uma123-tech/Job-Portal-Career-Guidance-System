# Job Portal & Career Guidance System

A Java Full Stack fresher/intermediate project using Java, JSP, Servlets, JDBC, MySQL, HTML and CSS.

## Features
- Registration and login with PBKDF2 password hashing
- Job search by title, company, location and job type
- Seeker dashboard with application history/status
- Apply to a job (one application per user/job)
- Recruiter dashboard to post jobs and view applicants
- Career guidance page with role-based skill roadmaps
- Prepared statements, session-based authentication, role checks

## Requirements (Windows)
1. JDK 11 or later
2. Maven 3.8+
3. MySQL Server 8+
4. Apache Tomcat 9.0.x (Tomcat 10 uses `jakarta.*`, while this project uses `javax.*`)
5. Eclipse IDE for Enterprise Java and Web Developers (or IntelliJ IDEA Ultimate)

## 1. Create the database
Open MySQL Workbench and run `database/schema.sql`.
The script creates the database and sample jobs. For a recruiter demo account, register normally as a seeker, then run the SQL update shown at the end of `database/schema.sql` with the email you registered.

## 2. Configure database connection
Edit `src/main/java/com/jobportal/util/DBConnection.java`.
Set `DB_USER` and `DB_PASSWORD` to your local MySQL credentials. For a local college project, keep credentials out of any public GitHub repository.

## 3. Build the WAR
Open PowerShell in the project root:
```powershell
mvn clean package
```
The build output should be `target\JobPortalCareerGuidance.war`.

## 4. Deploy to Tomcat 9
- Copy `target\JobPortalCareerGuidance.war` to `<TOMCAT_HOME>\webapps\`
- Start Tomcat using `<TOMCAT_HOME>\bin\startup.bat`
- Open `http://localhost:8080/JobPortalCareerGuidance/`

Alternatively, import the project as an **Existing Maven Project** in Eclipse, add a Tomcat 9 server, and run on server.

## 5. Test workflow
1. Register a new account. New accounts have the `SEEKER` role.
2. Log in and browse/apply for jobs.
3. To enable recruiter features, update your registered account to `RECRUITER` in MySQL:
   ```sql
   USE job_portal;
   UPDATE users SET role='RECRUITER' WHERE email='your-email@example.com';
   ```
4. Log out and back in, then open **Recruiter dashboard** to post jobs and view applicants.
5. To load sample listings, open `database/sample-jobs.sql`, replace `recruiter@example.com` with your recruiter account email, and run the script in MySQL Workbench.
6. Test application status updates by editing the status in the `applications` table (a recruiter status-update UI can be added as an extension).

## Troubleshooting
- `Access denied for user`: update the credentials in `DBConnection.java`.
- `Communications link failure`: confirm MySQL Server is running and port 3306 is available.
- `HTTP Status 404`: confirm Tomcat 9 is running and the URL includes `/JobPortalCareerGuidance/`.
- `javax.servlet...` compilation errors: use Tomcat 9, not Tomcat 10/11.
- `mvn` not recognized: install Maven and add its `bin` folder to PATH, or run Maven through your IDE.

## Suggested improvements
Add resume upload, email notifications, pagination, admin moderation, application status update UI, and automated tests. Do not store plain-text passwords or commit secrets.
