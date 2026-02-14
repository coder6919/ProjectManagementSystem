package controller;

import dao.TaskDAO;
import model.Task;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/task")
public class TaskServlet extends HttpServlet {
    private TaskDAO taskDAO = new TaskDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Retrieve form data
        String name = request.getParameter("name");
        int projectId = Integer.parseInt(request.getParameter("projectId"));
        int resourceId = Integer.parseInt(request.getParameter("resourceId"));

        // Create Task object
        Task task = new Task();
        task.setName(name);
        task.setProjectId(projectId);
        task.setResourceId(resourceId);

        // Call DAO to add task with validation logic
        String result = taskDAO.addTask(task);

        if (result.startsWith("Error")) {
            // Requirement 7: Send error message back to UI if validation fails
            request.setAttribute("errorMessage", result);
            request.setAttribute("projectId", projectId);
            request.getRequestDispatcher("add-task.jsp").forward(request, response);
        } else {
            // Success: Redirect back to project list
            response.sendRedirect("project?action=list");
        }
    }
}