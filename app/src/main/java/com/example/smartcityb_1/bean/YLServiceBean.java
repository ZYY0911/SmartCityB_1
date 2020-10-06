package com.example.smartcityb_1.bean;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/6 at 10:43
 */
public class YLServiceBean  {
    private String name,time,type,state;

    public YLServiceBean() {
    }

    public YLServiceBean(String name, String time, String type, String state) {
        this.name = name;
        this.time = time;
        this.type = type;
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
