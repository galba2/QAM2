package model;

public class AreaCount {

    private String area;
    private int areaCount;

    public AreaCount(String area, int areaCount) {

        this.area = area;
        this.areaCount = areaCount;
    }

    public AreaCount(){

        this.area = null;
        this.areaCount = -1;

    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public int getAreaCount() {
        return areaCount;
    }

    public void setAreaCount(int areaCount) {
        this.areaCount = areaCount;
    }
}
