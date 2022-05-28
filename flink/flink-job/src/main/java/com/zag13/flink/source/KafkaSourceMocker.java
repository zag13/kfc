package com.zag13.flink.source;

import com.zag13.flink.model.UserBehavior;
import org.apache.flink.streaming.api.functions.source.SourceFunction;

import java.util.Date;
import java.util.Random;


public class KafkaSourceMocker implements SourceFunction<UserBehavior> {
    private volatile boolean isRunning = true;
    String devices[] = {"Android", "IOS", "H5"};
    String actions[] = {"Pay", "login", "logout"};

    @Override
    public void run(SourceContext<UserBehavior> ctx) throws Exception {
        while (isRunning) {
            Thread.sleep(10);

            String userID = String.valueOf(new Random().nextInt(10000));
            String deviceType = devices[(int) (Math.random() * devices.length)];
            String action = actions[(int) (Math.random() * actions.length)];
            String eventTime = new Date().toString();
            ctx.collect(new UserBehavior(userID, deviceType, action, eventTime));
        }
    }

    @Override
    public void cancel() {
        isRunning = false;
    }
}
