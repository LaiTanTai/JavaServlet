package Entity;

import java.sql.Date;

public class TaskEntity {
    private int id;
    private String name ;
    private Date start;
    private Date end;
    private String username ;
    private String status ;
    private String project;
    private boolean haveget = false;

    public boolean isHaveget() {
        return haveget;
    }

    public void setHaveget(boolean haveget) {
        this.haveget = haveget;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public int getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public Date getEnd() {
        return end;
    }

    public Date getStart() {
        return start;
    }

    public String getProject() {
        return project;
    }
}
