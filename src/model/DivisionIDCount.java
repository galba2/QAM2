package model;

/**
 * This class is the model for DivisionIDCount.
 */
public class DivisionIDCount {

    private int divID;
    private int divCount;

    /** Constructor DivisionIDCount
     * @param divID divID
     * @param divCount divCount
     */
    public DivisionIDCount(int divID, int divCount) {
        this.divID = divID;
        this.divCount = divCount;
    }

    /** This method gets id
     * @return
     */
    public int getDivID() {
        return this.divID;
    }

    /** This method sets id
     * @param divID id
     */
    public void setDivID(int divID) {
        this.divID = divID;
    }

    /** This method gets divCount
     * @return divCount
     */
    public int getDivCount() {
        return divCount;
    }

    /** This method sets divCount
     * @param divCount
     */
    public void setDivCount(int divCount) {
        this.divCount = divCount;
    }
}
