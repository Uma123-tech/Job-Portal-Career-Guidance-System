package com.jobportal.dao;

import com.jobportal.model.Job;
import com.jobportal.util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JobDAO {
    private Job map(ResultSet rs) throws SQLException {
        return new Job(rs.getInt("id"), rs.getString("title"), rs.getString("company"),
            rs.getString("location"), rs.getString("job_type"), rs.getString("salary_range"),
            rs.getString("description"), rs.getString("required_skills"), rs.getString("recruiter_name"),
            rs.getTimestamp("created_at"));
    }

    public List<Job> search(String q, String location) throws SQLException {
        List<Job> jobs = new ArrayList<>();
        String sql = "SELECT j.*, u.full_name AS recruiter_name FROM jobs j JOIN users u ON j.recruiter_id=u.id " +
            "WHERE (?='' OR j.title LIKE ? OR j.company LIKE ? OR j.required_skills LIKE ?) " +
            "AND (?='' OR j.location LIKE ?) ORDER BY j.created_at DESC";
        try (Connection c=DBConnection.getConnection(); PreparedStatement ps=c.prepareStatement(sql)) {
            String term = "%" + q + "%", loc = "%" + location + "%";
            ps.setString(1,q); ps.setString(2,term); ps.setString(3,term); ps.setString(4,term);
            ps.setString(5,location); ps.setString(6,loc);
            try(ResultSet rs=ps.executeQuery()){ while(rs.next()) jobs.add(map(rs)); }
        }
        return jobs;
    }

    public Job findById(int id) throws SQLException {
        String sql="SELECT j.*,u.full_name AS recruiter_name FROM jobs j JOIN users u ON j.recruiter_id=u.id WHERE j.id=?";
        try(Connection c=DBConnection.getConnection(); PreparedStatement ps=c.prepareStatement(sql)){
            ps.setInt(1,id); try(ResultSet rs=ps.executeQuery()){return rs.next()?map(rs):null;}
        }
    }

    public boolean create(int recruiterId, String title, String company, String location, String type,
                          String salary, String description, String skills) throws SQLException {
        String sql="INSERT INTO jobs(recruiter_id,title,company,location,job_type,salary_range,description,required_skills) VALUES(?,?,?,?,?,?,?,?)";
        try(Connection c=DBConnection.getConnection(); PreparedStatement ps=c.prepareStatement(sql)){
            ps.setInt(1,recruiterId); ps.setString(2,title); ps.setString(3,company); ps.setString(4,location);
            ps.setString(5,type); ps.setString(6,salary); ps.setString(7,description); ps.setString(8,skills);
            return ps.executeUpdate()==1;
        }
    }

    public List<Job> byRecruiter(int recruiterId) throws SQLException {
        List<Job> jobs=new ArrayList<>();
        String sql="SELECT j.*,u.full_name AS recruiter_name FROM jobs j JOIN users u ON j.recruiter_id=u.id WHERE j.recruiter_id=? ORDER BY j.created_at DESC";
        try(Connection c=DBConnection.getConnection(); PreparedStatement ps=c.prepareStatement(sql)){
            ps.setInt(1,recruiterId); try(ResultSet rs=ps.executeQuery()){while(rs.next())jobs.add(map(rs));}
        }
        return jobs;
    }
}
