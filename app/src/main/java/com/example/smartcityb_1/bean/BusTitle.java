package com.example.smartcityb_1.bean;

import java.io.Serializable;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/8 at 11:27
 */
public class BusTitle  implements Serializable {

    /**
     * busid : 1
     * pathName : 1路
     * startSite : 火车站
     * endSite : 德州东站
     * runTime1 : 5：30-22：00
     * runTime2 : 6：00-21：30
     * price : 2
     * mileage : 20
     */

    private int busid;
    private String pathName;
    private String startSite;
    private String endSite;
    private String runTime1;
    private String runTime2;
    private int price;
    private int mileage;

    public int getBusid() {
        return busid;
    }

    public void setBusid(int busid) {
        this.busid = busid;
    }

    public String getPathName() {
        return pathName;
    }

    public void setPathName(String pathName) {
        this.pathName = pathName;
    }

    public String getStartSite() {
        return startSite;
    }

    public void setStartSite(String startSite) {
        this.startSite = startSite;
    }

    public String getEndSite() {
        return endSite;
    }

    public void setEndSite(String endSite) {
        this.endSite = endSite;
    }

    public String getRunTime1() {
        return runTime1;
    }

    public void setRunTime1(String runTime1) {
        this.runTime1 = runTime1;
    }

    public String getRunTime2() {
        return runTime2;
    }

    public void setRunTime2(String runTime2) {
        this.runTime2 = runTime2;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }
}
