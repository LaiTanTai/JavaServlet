package payload.response;

import java.util.List;

public class ProfileJobTask {
        private List<UserTask> userTaskList;
        private String Fullname;
        private int NotDone;
        private int Inprocess;
        private int Done;

    public void setFullname(String fullname) {
        Fullname = fullname;
    }

    public String getFullname() {
        return Fullname;
    }

    public void setNotDone(int notDone) {
        NotDone = notDone;
    }

    public void setInprocess(int inprocess) {
        Inprocess = inprocess;
    }

    public void setDone(int done) {
        Done = done;
    }

    public void setUserTaskList(List<UserTask> userTaskList) {
        this.userTaskList = userTaskList;
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

    public List<UserTask> getUserTaskList() {
        return userTaskList;
    }
}
