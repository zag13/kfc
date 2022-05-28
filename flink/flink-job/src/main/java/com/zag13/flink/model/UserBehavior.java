package com.zag13.flink.model;

import java.util.Date;

public class UserBehavior {

    // 用户ID
    private String userId;

    // 设备端 IOS Android H5
    private String deviceType;

    // 用户关键行为 visit start register login pay
    private String action;

    // 触发时间
    private String eventTime;

    public UserBehavior(String userId, String deviceType, String action, String eventTime) {
        this.userId = userId;
        this.deviceType = deviceType;
        this.action = action;
        this.eventTime = eventTime;
    }

    public String getUserId() {
        return userId;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public String getAction() {
        return action;
    }

    public String getEventTime() {
        return eventTime;
    }

}
