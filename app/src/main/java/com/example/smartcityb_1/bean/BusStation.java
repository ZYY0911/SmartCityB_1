package com.example.smartcityb_1.bean;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/8 at 11:36
 */
public class BusStation  {

    /**
     * id : 1
     * busid : 1
     * siteName : 火车站
     */

    private int id;
    private int busid;
    private String siteName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBusid() {
        return busid;
    }

    public void setBusid(int busid) {
        this.busid = busid;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }
}
