package dao;

import model.Task;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskDAO {

    Connection conn = DBConnection.getConnection();

    // CHECK TASK COUNT FOR RESOURCE IN SAME PROJECT
    public int getTaskCount(int resourceId, int projectId) {
        int count = 0;

        try {
            String sql = "SELECT COUNT(*) FROM tasks WHERE resource_id=? AND project_id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, resourceId);
            ps.setInt(2, projectId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                count = rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return count;
    }

    // CHECK RESOURCE STATUS
    public boolean isResourceAvailable(int resourceId) {
        boolean available = false;

        try {
            String sql = "SELECT status FROM resources WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, resourceId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                available = rs.getString("status").equalsIgnoreCase("Available");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return available;
    }

    // ADD TASK WITH VALIDATION
    public String addTask(Task task) {
        try {
            // Requirement 6: Check Resource Status
            if (!isResourceAvailable(task.getResourceId())) {
                return "Error: Only 'Available' resources can be assigned to new tasks.";
            }

            // Requirement 5 & 7: Check Allocation Constraint
            int currentTasks = getTaskCount(task.getResourceId(), task.getProjectId());
            if (currentTasks >= 2) {
                return "Error: This resource is already assigned to 2 tasks in this project.";
            }

            // Proceed to insert the task
            String sql = "INSERT INTO tasks (name, project_id, resource_id) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, task.getName());
            ps.setInt(2, task.getProjectId());
            ps.setInt(3, task.getResourceId());
            ps.executeUpdate();

            return "Task Added Successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }

    // GET ALL TASKS
    public List<Task> getAllTasks() {

        List<Task> list = new ArrayList<>();

        try {
            String sql = "SELECT * FROM tasks";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Task t = new Task();
                t.setId(rs.getInt("id"));
                t.setName(rs.getString("name"));
                t.setProjectId(rs.getInt("project_id"));
                t.setResourceId(rs.getInt("resource_id"));

                list.add(t);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
