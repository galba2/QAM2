package model;

public class AppointmentType {

    private String apptType;
    private int apptTypeCount;

    public AppointmentType(String apptType, int apptTypeCount) {
        this.apptType = apptType;
        this.apptTypeCount = apptTypeCount;
    }

    public String getApptType() {
        return apptType;
    }

    public void setApptType(String apptType) {
        this.apptType = apptType;
    }

    public int getApptTypeCount() {
        return apptTypeCount;
    }

    public void setApptTypeCount(int apptTypeCount) {
        this.apptTypeCount = apptTypeCount;
    }
}
