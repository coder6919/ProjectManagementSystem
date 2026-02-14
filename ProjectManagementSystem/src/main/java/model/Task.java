package model;

public class Task {

    private int id;
    private String name;
    private int projectId;
    private int resourceId;

    public Task() {}

    public Task(int id, String name, int projectId, int resourceId) {
        this.id = id;
        this.name = name;
        this.projectId = projectId;
        this.resourceId = resourceId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }
}
