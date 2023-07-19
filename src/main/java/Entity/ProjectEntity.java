package Entity;

import java.sql.Date;

public class ProjectEntity {
    private int id ;
    private String project_name ;

    private Date start ;
    private Date end;

    public void setEnd(Date end) {
        this.end = end;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public Date getStart() {
        return start;
    }

    public int getId() {
        return id;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }
}
