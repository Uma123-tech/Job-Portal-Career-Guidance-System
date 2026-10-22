
# Job Portal & Career Guidance System

A Java Full Stack web application built using Java, JSP, Servlets, JDBC, MySQL, HTML, and CSS.

## Features
- User registration and login with PBKDF2 password hashing
- Job search by title, company, location, and job type
- Job seeker dashboard with application history
- Apply for jobs
- Recruiter dashboard to post jobs and view applicants
- Career guidance page with skill roadmaps
- Prepared statements, session-based authentication, and role checks

## Technologies Used
- Java 11+
- JSP and Servlets
- JDBC
- MySQL 8+
- HTML and CSS
- Maven
- Apache Tomcat 9

## Setup Instructions

### 1. Create the database
Open MySQL Workbench and run `database/schema.sql`.

### 2. Configure database connection
Edit `src/main/java/com/jobportal/util/DBConnection.java`.

Set `DB_USER` and `DB_PASSWORD` to your local MySQL credentials. Never commit real passwords to a public repository.

### 3. Build the project
Run this command from the project root:

```powershell
mvn clean package
```

The WAR file will be generated at `target/JobPortalCareerGuidance.war`.

### 4. Deploy to Tomcat
Copy the WAR file into Tomcat 9's `webapps` folder and start Tomcat.

Open: http://localhost:8080/JobPortalCareerGuidance/

### 5. Test the application
1. Register a new account.
2. Log in and browse available jobs.
3. Apply for a job.
4. To test recruiter features, update your registered account's role in MySQL.
5. Log out and log back in to access the recruiter dashboard.

## Troubleshooting
- **Access denied:** Check the MySQL credentials in `DBConnection.java`.
- **Connection failure:** Confirm MySQL Server is running on port 3306.
- **HTTP 404:** Confirm Tomcat 9 is running and the application is deployed.
- **Maven not recognized:** Install Maven and configure its PATH.

## Future Improvements
- Resume upload
- Email notifications
- Pagination
- Admin moderation
- Application status update interface
- Automated tests
## Screenshots

### Home Page
![CareerLaunch Home Page](screenshots/Screenshot%202026-10-22%20161823.png)