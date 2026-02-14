package dao;

import model.Project;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectDAO {

    Connection conn = DBConnection.getConnection();

    // CREATE PROJECT
    public boolean addProject(Project project) {
        boolean status = false;
        try {
            String sql = "INSERT INTO projects(name, description) VALUES (?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, project.getName());
            ps.setString(2, project.getDescription());

            status = ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    // GET ALL PROJECTS
    public List<Project> getAllProjects() {
        List<Project> list = new ArrayList<>();

        try {
            String sql = "SELECT * FROM projects";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Project p = new Project();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setDescription(rs.getString("description"));

                list.add(p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // DELETE PROJECT
    public boolean deleteProject(int id) {
        boolean status = false;
        try {
            String sql = "DELETE FROM projects WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            status = ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }
}
