package model;

/**
 * This class is the model for AppointmentMonth.
 */
public class AppointmentMonth {

    private String apptMonth;
    private int apptMonthCount;

    /** Constructor AppointmentMonth
     * @param apptMonth month appointment
     * @param apptMonthCount month count
     */
    public AppointmentMonth(String apptMonth, int apptMonthCount) {
        this.apptMonth = apptMonth;
        this.apptMonthCount = apptMonthCount;
    }

    /** This method gets apptMonth
     * @return apptMonth
     */
    public String getApptMonth() {
        return apptMonth;
    }

    /** This method sets apptMonth
     * @param apptMonth
     */
    public void setApptMonth(String apptMonth) {
        this.apptMonth = apptMonth;
    }

    /** This method gets apptMonthCount
     * @return
     */
    public int getApptMonthCount() {
        return apptMonthCount;
    }

    /** This method sets apptMonthCount
     * @param apptMonthCount
     */
    public void setApptMonthCount(int apptMonthCount) {
        this.apptMonthCount = apptMonthCount;
    }
}
