package com.zag13.flink.transform;

import com.alibaba.fastjson.JSONObject;
import com.zag13.flink.model.UserBehavior;
import org.apache.flink.api.common.functions.MapFunction;

public class UserBehaviorMapper implements MapFunction<String, UserBehavior> {

    public UserBehavior map(String s) throws Exception {
        UserBehavior result;
        if (s.contains("message")) {
            JSONObject jsonObject = JSONObject.parseObject(s);
            String message = jsonObject.getString("message");
//            String[] messages = message.split("\\|");
            result = JSONObject.parseObject(message, UserBehavior.class);
        } else {
            result = JSONObject.parseObject(s, UserBehavior.class);
        }
        return result;
    }

}
