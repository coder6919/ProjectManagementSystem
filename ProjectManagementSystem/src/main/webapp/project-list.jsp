<%@ page import="java.util.List" %>
<%@ page import="model.Project" %>

<h2>Project List</h2>

<a href="add-project.jsp">Add Project</a>

<table border="1">
<tr>
    <th>ID</th>
    <th>Name</th>
    <th>Description</th>
    <th>Action</th>
</tr>

<%
@SuppressWarnings("unchecked")
List<Project> list =
        (List<Project>) request.getAttribute("projectList");

if(list != null){
    for(Project p : list){
%>

<tr>
    <td><%= p.getId() %></td>
    <td><%= p.getName() %></td>
    <td><%= p.getDescription() %></td>
    <td>
        <a href="project?action=delete&id=<%=p.getId()%>">Delete</a>
        <a href="project?action=delete&id=<%=p.getId()%>" class="btn btn-danger btn-sm">Delete</a>
        <a href="project?action=allocateTask&id=<%=p.getId()%>" class="btn btn-info btn-sm">Assign Task</a>
    </td>
</tr>

<%
    }
}
%>

</table>
