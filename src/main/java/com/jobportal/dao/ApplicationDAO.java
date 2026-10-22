package com.jobportal.dao;

import com.jobportal.util.DBConnection;
import java.sql.*;
import java.util.*;

public class ApplicationDAO {
    public boolean apply(int jobId, int seekerId) throws SQLException {
        String sql="INSERT INTO applications(job_id,seeker_id) VALUES(?,?)";
        try(Connection c=DBConnection.getConnection(); PreparedStatement ps=c.prepareStatement(sql)){
            ps.setInt(1,jobId); ps.setInt(2,seekerId); return ps.executeUpdate()==1;
        }
    }

    public List<Map<String,String>> forSeeker(int seekerId) throws SQLException {
        List<Map<String,String>> list=new ArrayList<>();
        String sql="SELECT a.status,a.applied_at,j.title,j.company,j.location FROM applications a JOIN jobs j ON a.job_id=j.id WHERE a.seeker_id=? ORDER BY a.applied_at DESC";
        try(Connection c=DBConnection.getConnection();PreparedStatement ps=c.prepareStatement(sql)){
            ps.setInt(1,seekerId);try(ResultSet rs=ps.executeQuery()){while(rs.next()){
                Map<String,String> row=new HashMap<>();
                row.put("status",rs.getString("status"));row.put("appliedAt",rs.getTimestamp("applied_at").toString());
                row.put("title",rs.getString("title"));row.put("company",rs.getString("company"));row.put("location",rs.getString("location"));
                list.add(row);
            }}
        } return list;
    }

    public List<Map<String,String>> applicantsForRecruiter(int recruiterId) throws SQLException {
        List<Map<String,String>> list=new ArrayList<>();
        String sql="SELECT u.full_name,u.email,j.title,a.status,a.applied_at FROM applications a JOIN jobs j ON a.job_id=j.id JOIN users u ON a.seeker_id=u.id WHERE j.recruiter_id=? ORDER BY a.applied_at DESC";
        try(Connection c=DBConnection.getConnection();PreparedStatement ps=c.prepareStatement(sql)){
            ps.setInt(1,recruiterId);try(ResultSet rs=ps.executeQuery()){while(rs.next()){
                Map<String,String> row=new HashMap<>();
                row.put("name",rs.getString("full_name"));row.put("email",rs.getString("email"));row.put("title",rs.getString("title"));
                row.put("status",rs.getString("status"));row.put("appliedAt",rs.getTimestamp("applied_at").toString());list.add(row);
            }}
        } return list;
    }
}
