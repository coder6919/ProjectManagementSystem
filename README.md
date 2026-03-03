Project Management System (Java MVC)
A full-stack Java web application for managing projects, resources, and task allocations. This project was developed as part of a technical machine test and follows the Model-View-Controller (MVC) architectural pattern.

🚀 Key Features
Project CRUD: Create and list projects.

Task Allocation: Assign tasks to specific developers within a project.

Smart Validation:

Prevents assigning a resource to more than 2 tasks in the same project.

Only allows assignment of resources with an "Available" status.

MySQL Persistence: All data is stored in a relational database.

🛠️ Prerequisites
Before running the project, ensure you have the following installed:

JDK: Version 21 or 25.

Server: Apache Tomcat 10.x (Important: This project uses the jakarta.* namespace).

Database: MySQL Server 8.0+.

IDE: IntelliJ IDEA (Community or Ultimate) or Eclipse.

⚙️ Setup Instructions
1. Database Setup
Open MySQL Workbench.

Run the script provided in db-scripts/schema.sql to create the database and tables.

(Optional) Insert sample data into the resources table with status 'Available'.

2. Configure Database Connection
Navigate to src/main/java/util/DBConnection.java.

Update the URL, USER, and PASSWORD variables to match your local MySQL credentials.

3. Library Dependencies
Because this is a raw JEE project (no Maven), you must manually link the following JAR files in your IDE:

servlet-api.jar (Found in Tomcat/lib).

jsp-api.jar (Found in Tomcat/lib).

mysql-connector-j.jar (Download and add to project libraries).

4. Running the Application
IntelliJ: Use the Smart Tomcat plugin.

Configuration:

Set Deployment Directory to src/main/webapp.

Set Context Path to /ProjectManagementSystem.

Access:

Project List: http://localhost:8080/ProjectManagementSystem/project?action=list

Add Project: http://localhost:8080/ProjectManagementSystem/add-project.jsp
