package com.example.smartcityb_1.bean;

import java.io.Serializable;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/2 at 9:10
 */
public class SubwayStation  implements Serializable {


    /**
     * subwayid : 1
     * name : 地铁1号线
     * nextname : 万寿路站
     * time : 41
     */

    private int subwayid;
    private String name;
    private String nextname;
    private int time;

    public int getSubwayid() {
        return subwayid;
    }

    public void setSubwayid(int subwayid) {
        this.subwayid = subwayid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNextname() {
        return nextname;
    }

    public void setNextname(String nextname) {
        this.nextname = nextname;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
