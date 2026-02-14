package dao;

import model.Resource;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ResourceDAO {

    Connection conn = DBConnection.getConnection();

    // ADD RESOURCE
    public boolean addResource(Resource resource) {
        boolean status = false;
        try {
            String sql = "INSERT INTO resources(name, status) VALUES (?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, resource.getName());
            ps.setString(2, resource.getStatus());

            status = ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    // GET ALL RESOURCES
    public List<Resource> getAllResources() {
        List<Resource> list = new ArrayList<>();

        try {
            String sql = "SELECT * FROM resources";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Resource r = new Resource();
                r.setId(rs.getInt("id"));
                r.setName(rs.getString("name"));
                r.setStatus(rs.getString("status"));

                list.add(r);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // GET ONLY AVAILABLE RESOURCES
    public List<Resource> getAvailableResources() {
        List<Resource> list = new ArrayList<>();

        try {
            String sql = "SELECT * FROM resources WHERE status='Available'";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Resource r = new Resource();
                r.setId(rs.getInt("id"));
                r.setName(rs.getString("name"));
                r.setStatus(rs.getString("status"));

                list.add(r);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // UPDATE RESOURCE STATUS
    public void updateStatus(int resourceId, String status) {
        try {
            String sql = "UPDATE resources SET status=? WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, status);
            ps.setInt(2, resourceId);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
