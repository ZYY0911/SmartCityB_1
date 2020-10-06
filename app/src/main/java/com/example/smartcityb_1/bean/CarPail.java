package com.example.smartcityb_1.bean;

import java.io.Serializable;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/6 at 9:04
 */
public class CarPail  implements Serializable {

    /**
     * id : 1
     * carid : 鲁A10001
     * time : 2020-01-02 18:09:11
     * place : 幸福大道路口
     * deductPoints : 2
     * cost : 100
     * status : 1
     * description : 违章停车
     * notification : 100001
     */

    private int id;
    private String carid;
    private String time;
    private String place;
    private int deductPoints;
    private int cost;
    private int status;
    private String description;
    private String notification;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCarid() {
        return carid;
    }

    public void setCarid(String carid) {
        this.carid = carid;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getDeductPoints() {
        return deductPoints;
    }

    public void setDeductPoints(int deductPoints) {
        this.deductPoints = deductPoints;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }
}
