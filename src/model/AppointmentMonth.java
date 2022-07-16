package model;

public class AppointmentMonth {

    private String apptMonth;
    private int apptMonthCount;

    public AppointmentMonth(String apptMonth, int apptMonthCount) {
        this.apptMonth = apptMonth;
        this.apptMonthCount = apptMonthCount;
    }

    public String getApptMonth() {
        return apptMonth;
    }

    public void setApptMonth(String apptMonth) {
        this.apptMonth = apptMonth;
    }

    public int getApptMonthCount() {
        return apptMonthCount;
    }

    public void setApptMonthCount(int apptMonthCount) {
        this.apptMonthCount = apptMonthCount;
    }
}
