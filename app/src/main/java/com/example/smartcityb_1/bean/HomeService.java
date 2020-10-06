package com.example.smartcityb_1.bean;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/5 at 17:41
 */
public class HomeService {

    /**
     * id : 1
     * name : 便民服务
     * image : http://localhost:8080/mobileA/images/tubiao1.png
     * url : https://new.qq.com/omn/20201003/20201003A06MRV00.html
     * type : 智慧服务
     */

    private int id;
    private String name;
    private String image;
    private String url;
    private String type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
