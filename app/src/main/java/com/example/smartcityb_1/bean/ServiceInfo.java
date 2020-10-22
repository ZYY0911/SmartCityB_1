package com.example.smartcityb_1.bean;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/2 at 15:03
 */
public class ServiceInfo {


    /**
     * serviceid : 1
     * serviceName : 便民服务
     * icon : http://192.168.155.108:8080/mobileA/images/tubiao1.png
     * url : https://new.qq.com/omn/20201003/20201003A06MRV00.html
     * serviceType : 智慧服务
     */

    private int serviceid;
    private String serviceName;
    private String icon;
    private String url;
    private String serviceType;

    public int getServiceid() {
        return serviceid;
    }

    public void setServiceid(int serviceid) {
        this.serviceid = serviceid;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }
}
