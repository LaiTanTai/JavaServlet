package payload.response;

import Entity.TaskEntity;

import java.sql.Date;
import java.util.List;

public class Groupwork_details {
    private String name;
    private int NotDone = 0;
    private int Inprocess = 0 ;
    private int Done = 0 ;
    private List<UserTask> taskList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getInprocess() {
        return Inprocess;
    }

    public int getNotDone() {
        return NotDone;
    }

    public int getDone() {
        return Done;
    }

    public List<UserTask> getTaskList() {
        return taskList;
    }

    public void setInprocess(int inprocess) {
        Inprocess = inprocess;
    }

    public void setTaskList(List<UserTask> taskList) {
        this.taskList = taskList;
    }

    public void setDone(int done) {
        Done = done;
    }

    public void setNotDone(int notDone) {
        NotDone = notDone;
    }
}
