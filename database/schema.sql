CREATE DATABASE IF NOT EXISTS job_portal
  CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE job_portal;

CREATE TABLE IF NOT EXISTS users (
  id INT PRIMARY KEY AUTO_INCREMENT,
  full_name VARCHAR(100) NOT NULL,
  email VARCHAR(150) NOT NULL UNIQUE,
  password_hash VARCHAR(255) NOT NULL,
  role ENUM('SEEKER','RECRUITER','ADMIN') NOT NULL DEFAULT 'SEEKER',
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS jobs (
  id INT PRIMARY KEY AUTO_INCREMENT,
  recruiter_id INT NOT NULL,
  title VARCHAR(150) NOT NULL,
  company VARCHAR(150) NOT NULL,
  location VARCHAR(120) NOT NULL,
  job_type ENUM('FULL_TIME','PART_TIME','INTERNSHIP','CONTRACT') NOT NULL DEFAULT 'FULL_TIME',
  salary_range VARCHAR(100),
  description TEXT NOT NULL,
  required_skills VARCHAR(500),
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT fk_jobs_recruiter FOREIGN KEY (recruiter_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS applications (
  id INT PRIMARY KEY AUTO_INCREMENT,
  job_id INT NOT NULL,
  seeker_id INT NOT NULL,
  status ENUM('APPLIED','UNDER_REVIEW','SHORTLISTED','REJECTED','HIRED') NOT NULL DEFAULT 'APPLIED',
  applied_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT fk_app_job FOREIGN KEY (job_id) REFERENCES jobs(id) ON DELETE CASCADE,
  CONSTRAINT fk_app_seeker FOREIGN KEY (seeker_id) REFERENCES users(id) ON DELETE CASCADE,
  CONSTRAINT uq_user_job UNIQUE (job_id, seeker_id)
);

-- Sample listings are inserted after a recruiter account exists. To make your own
-- registered account a recruiter, replace the email below and run:
-- UPDATE users SET role='RECRUITER' WHERE email='your-email@example.com';
-- Then log out and back in.
