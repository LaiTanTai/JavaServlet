package payload.response;

import java.sql.Date;

public class UserTask {
    private int id ;
    private String name ;
    private String username;
    private Date start;
    private Date end ;
    private String job_name ;
    private String status;

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setJob_name(String job_name) {
        this.job_name = job_name;
    }

    public Date getStart() {
        return start;
    }

    public String getStatus() {
        return status;
    }

    public Date getEnd() {
        return end;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getJob_name() {
        return job_name;
    }
}
