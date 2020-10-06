package com.example.smartcityb_1.bean;

import java.io.Serializable;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/5 at 20:13
 */
public class UserInfos  implements Serializable {

    /**
     * id : 371402199902041133
     * name : 赵子涵
     * avatar : http://localhost:8080/mobileA/images/user1.png
     * phone : 13505347777
     * sex : 女
     */

    private String id;
    private String userid;
    private String name;
    private String avatar;
    private String phone;
    private String sex;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
