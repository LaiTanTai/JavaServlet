package payload.response;

import java.util.Date;

public class UserJobTask {
    private String jobname ;
    private String taskName ;
    private String status;
    private Date start_date;
    private Date end_date;


    public void setJobname(String jobname) {
        this.jobname = jobname;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public String getJobname() {
        return jobname;
    }

    public Date getStart_date() {
        return start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public String getStatus() {
        return status;
    }

    public String getTaskName() {
        return taskName;
    }
}
