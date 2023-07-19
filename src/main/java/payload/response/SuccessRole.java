package payload.response;

public class SuccessRole {
    private int role_id;
    private boolean iSsuccess;

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public void setiSsuccess(boolean iSsuccess) {
        this.iSsuccess = iSsuccess;
    }
    public boolean getiSsuccess() {
        return iSsuccess;
    }
}
