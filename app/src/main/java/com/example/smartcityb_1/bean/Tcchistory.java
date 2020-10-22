package com.example.smartcityb_1.bean;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/8 at 9:34
 */
public class Tcchistory  {

    /**
     * id : 1
     * carNum : 鲁N98001
     * charge : 6
     * inTime : 2020-09-30 12:56:04
     * outTime : 2020-09-30 14:56:16
     * parkingid : 1
     */

    private int id;
    private String carNum;
    private String charge;
    private String inTime;
    private String outTime;
    private String parkingid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCarNum() {
        return carNum;
    }

    public void setCarNum(String carNum) {
        this.carNum = carNum;
    }

    public String getCharge() {
        return charge;
    }

    public void setCharge(String charge) {
        this.charge = charge;
    }

    public String getInTime() {
        return inTime;
    }

    public void setInTime(String inTime) {
        this.inTime = inTime;
    }

    public String getOutTime() {
        return outTime;
    }

    public void setOutTime(String outTime) {
        this.outTime = outTime;
    }

    public String getParkingid() {
        return parkingid;
    }

    public void setParkingid(String parkingid) {
        this.parkingid = parkingid;
    }
}
