package com.zag13.flink;

import com.zag13.flink.model.UserBehavior;
import com.zag13.flink.sink.ClickhouseSinker;
import com.zag13.flink.source.KafkaSource;
import com.zag13.flink.transform.UserBehaviorMapper;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class FlinkJob {

    public static void main(String[] args) throws Exception {
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        // source
        DataStreamSource<String> source = env.addSource(KafkaSource.getFlinkKafkaConsumer());

        // transform
        SingleOutputStreamOperator<UserBehavior> result = source
                .map(new UserBehaviorMapper());

        // sink
        result.print().name("Std Console Sinker");

        result.addSink(new ClickhouseSinker()).name("Clickhouse Sinker");

        // submit
        env.execute("FlinkJob");
    }

}
