package org.example.entity;

import java.util.Objects;

public class Task {
    private String project;
    private String description;
    private String assignee;
    private Priority priority;
    private Status status;

    // Testin kullandığı sıra: (Status, Priority)
    public Task(String project, String description, String assignee,
                Status status, Priority priority) {
        this.project = project;
        this.description = description;
        this.assignee = assignee;
        this.status = status;
        this.priority = priority;
    }

    // Alternatif sıra: (Priority, Status)
    public Task(String project, String description, String assignee,
                Priority priority, Status status) {
        this(project, description, assignee, status, priority);
    }

    public String getProject() { return project; }
    public String getDescription() { return description; }
    public String getAssignee() { return assignee; }
    public Priority getPriority() { return priority; }
    public Status getStatus() { return status; }

    public void setProject(String project) { this.project = project; }
    public void setDescription(String description) { this.description = description; }
    public void setAssignee(String assignee) { this.assignee = assignee; }
    public void setPriority(Priority priority) { this.priority = priority; }
    public void setStatus(Status status) { this.status = status; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;
        Task task = (Task) o;
        return Objects.equals(project, task.project) &&
                Objects.equals(description, task.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(project, description);
    }

    @Override
    public String toString() {
        return "Task{" +
                "project='" + project + '\'' +
                ", description='" + description + '\'' +
                ", assignee='" + assignee + '\'' +
                ", priority=" + priority +
                ", status=" + status +
                '}';
    }
}

