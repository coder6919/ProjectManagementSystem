<%@ page import="java.util.List" %>
<%@ page import="model.Resource" %>
<!DOCTYPE html>
<html>
<head>
    <title>Allocate Task</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container mt-5">

    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card shadow">
                <div class="card-header bg-primary text-white">
                    <h3 class="card-title mb-0">Assign Task to Developer</h3>
                </div>
                <div class="card-body">

                    <%-- Requirement 7: Validation logic to display appropriate error messages --%>
                    <%
                        String error = (String) request.getAttribute("errorMessage");
                        if (error != null) {
                    %>
                        <div class="alert alert-danger alert-dismissible fade show" role="alert">
                            <strong>Wait!</strong> <%= error %>
                            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                        </div>
                    <% } %>

                    <form action="task" method="post">
                        <%-- The project ID is passed from the controller to ensure context --%>
                        <input type="hidden" name="projectId" value="<%= request.getAttribute("projectId") %>">

                        <div class="mb-3">
                            <label class="form-label">Task Name</label>
                            <input type="text" name="name" class="form-control" placeholder="Enter task name" required>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Select Resource (Requirement 6: Available Only)</label>
                            <select name="resourceId" class="form-select" required>
                                <option value="">-- Choose a Developer --</option>
                                <%
                                   List<Resource> resources = (List<Resource>)request.getAttribute("resourceList");
                                   if(resources != null) {
                                       for(Resource r : resources) {
                                %>
                                    <option value="<%= r.getId() %>"><%= r.getName() %></option>
                                <%
                                       }
                                   }
                                %>
                            </select>
                        </div>

                        <div class="d-grid gap-2">
                            <button type="submit" class="btn btn-success">Save Task</button>
                            <a href="project?action=list" class="btn btn-outline-secondary">Back to Projects</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>