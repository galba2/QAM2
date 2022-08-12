package model;

/**
 * This class is the model for CustomerReport.
 */
public class CustomerReport {

    private String month;
    private String type;
    private int count;


    /** Constructor CustomerReport
     * @param month
     * @param type
     * @param count
     */
    public CustomerReport(String month, String type, int count) {
        this.month = month;
        this.type = type;
        this.count = count;
    }

    /** This method gets type
     * @return type
     */
    public String getType() {
        return type;
    }

    /** This method sets type
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /** This method gets count
     * @return count
     */
    public int getCount() {
        return count;
    }

    /** This method sets type
     * @param count
     */
    public void setCount(int count) {
        this.count = count;
    }

    /** This method gets month
     * @return month
     */
    public String getMonth() {
        return month;
    }

    /** This method sets type
     * @param month
     */
    public void setMonth(String month) {
        this.month = month;
    }
}
