package model;

/**
 * This class is the model for AreaCount.
 */
public class AreaCount {

    private String area;
    private int areaCount;


    /** Constructor AreaCount
     * @param area area
     * @param areaCount areaCount
     */
    public AreaCount(String area, int areaCount) {

        this.area = area;
        this.areaCount = areaCount;
    }

    /**
     * Constructor AreaCount
     */
    public AreaCount(){

        this.area = null;
        this.areaCount = -1;

    }

    /** This method gets area
     * @return area
     */
    public String getArea() {
        return area;
    }

    /**This method sets area
     * @param area area
     */
    public void setArea(String area) {
        this.area = area;
    }

    /** This method gets areaCount
     * @return areaCount
     */
    public int getAreaCount() {
        return areaCount;
    }

    /**This method sets areaCount
     * @param areaCount areaCount
     */
    public void setAreaCount(int areaCount) {
        this.areaCount = areaCount;
    }
}
