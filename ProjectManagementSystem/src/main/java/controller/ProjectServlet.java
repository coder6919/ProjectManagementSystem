package controller;

import dao.ProjectDAO;
import model.Project;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dao.ResourceDAO;
import java.io.IOException;
import java.util.List;

@WebServlet("/project")
public class ProjectServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private ProjectDAO dao;

    @Override
    public void init() {
        dao = new ProjectDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("add".equals(action)) {

            String name = request.getParameter("name");
            String description = request.getParameter("description");

            Project project = new Project();
            project.setName(name);
            project.setDescription(description);

            dao.addProject(project);

            response.sendRedirect("project?action=list");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action == null) {
            action = "list";
        }

        switch (action) {

            case "list":
                List<Project> list = dao.getAllProjects();
                request.setAttribute("projectList", list);

                request.getRequestDispatcher("project-list.jsp")
                        .forward(request, response);
                break;

            case "delete":
                int id = Integer.parseInt(request.getParameter("id"));
                dao.deleteProject(id);

                response.sendRedirect("project?action=list");
                break;
            case "allocateTask":
                int projectId = Integer.parseInt(request.getParameter("id"));
                request.setAttribute("projectId", projectId);

                request.setAttribute("resourceList", new ResourceDAO().getAvailableResources());
                request.getRequestDispatcher("add-task.jsp").forward(request, response);
                break;
        }
    }
}
