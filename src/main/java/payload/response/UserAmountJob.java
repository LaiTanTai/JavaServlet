package payload.response;

import java.util.List;

public class UserAmountJob {
    private List<UserJobTask> userJobTaskList;
    private int NotDone;
    private int Inprocess;
    private int Done;

    public void setDone(int done) {
        Done = done;
    }

    public void setInprocess(int inprocess) {
        Inprocess = inprocess;
    }

    public void setUserJobTaskList(List<UserJobTask> userJobTaskList) {
        this.userJobTaskList = userJobTaskList;
    }

    public void setNotDone(int notDone) {
        NotDone = notDone;
    }

    public int getDone() {
        return Done;
    }

    public int getInprocess() {
        return Inprocess;
    }

    public int getNotDone() {
        return NotDone;
    }

    public List<UserJobTask> getUserJobTaskList() {
        return userJobTaskList;
    }
}
