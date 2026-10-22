package com.jobportal.model;

import java.sql.Timestamp;

public class Job {
    private int id;
    private String title, company, location, jobType, salaryRange, description, requiredSkills, recruiterName;
    private Timestamp createdAt;

    public Job(int id, String title, String company, String location, String jobType,
               String salaryRange, String description, String requiredSkills, String recruiterName, Timestamp createdAt) {
        this.id=id; this.title=title; this.company=company; this.location=location; this.jobType=jobType;
        this.salaryRange=salaryRange; this.description=description; this.requiredSkills=requiredSkills;
        this.recruiterName=recruiterName; this.createdAt=createdAt;
    }
    public int getId(){return id;} public String getTitle(){return title;}
    public String getCompany(){return company;} public String getLocation(){return location;}
    public String getJobType(){return jobType;} public String getSalaryRange(){return salaryRange;}
    public String getDescription(){return description;} public String getRequiredSkills(){return requiredSkills;}
    public String getRecruiterName(){return recruiterName;} public Timestamp getCreatedAt(){return createdAt;}
}
