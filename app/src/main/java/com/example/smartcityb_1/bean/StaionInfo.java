package com.example.smartcityb_1.bean;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/2 at 9:29
 */
public class StaionInfo {

    /**
     * stationIndex : 1
     * stationname : 古城站
     * distance : 1000
     */

    private int stationIndex;
    private String stationname;
    private int distance;

    public int getStationIndex() {
        return stationIndex;
    }

    public void setStationIndex(int stationIndex) {
        this.stationIndex = stationIndex;
    }

    public String getStationname() {
        return stationname;
    }

    public void setStationname(String stationname) {
        this.stationname = stationname;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}
