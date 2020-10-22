package com.example.smartcityb_1.bean;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/8 at 14:55
 */
public class Rl_Bean  {
    private int day,month,index;
    private int lx;
    private int bg;

    public Rl_Bean() {
    }

    public int getBg() {
        return bg;
    }

    public void setBg(int bg) {
        this.bg = bg;
    }

    public Rl_Bean(int day, int month, int index, int lx, int bg) {
        this.day = day;
        this.month = month;
        this.index = index;
        this.lx = lx;
        this.bg = bg;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getLx() {
        return lx;
    }

    public void setLx(int lx) {
        this.lx = lx;
    }
}
