package com.zag13.flink.sink;

import com.zag13.flink.model.UserBehavior;
import org.apache.flink.streaming.api.functions.sink.SinkFunction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Properties;

import static com.zag13.flink.util.PropertyUtil.getProperties;

public class ClickhouseSinker implements SinkFunction<UserBehavior> {

    public void invoke(UserBehavior value, Context context) throws Exception {
        Properties properties = getProperties("clickhouse.properties");
        String address = properties.getProperty("jdbc.url");
        Class.forName(properties.getProperty("jdbc.driver"));

        Connection connection = DriverManager.getConnection(address);
        String sql = "insert into zag13.user_behavior (user_id, action, device_type, event_time) values(?,?,?,?);";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, value.getUserId());
        preparedStatement.setString(2, value.getAction());
        preparedStatement.setString(3, value.getDeviceType());
        preparedStatement.setString(4, value.getEventTime());

        preparedStatement.executeUpdate();            //执行sql语句
    }

}
