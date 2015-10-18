package com.anduo.learn.akka.java.actor;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import com.anduo.learn.akka.java.messages.MapData;
import com.anduo.learn.akka.java.messages.ReduceData;
import com.anduo.learn.akka.java.messages.WordCount;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

/**
 * ━━━━━━神兽出没━━━━━━
 * 　　　┏┓　　　┏┓
 * 　　┏┛┻━━━┛┻┓
 * 　　┃　　　　　　　┃
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　　　　　┃
 * 　　┃　　　┻　　　┃
 * 　　┃　　　　　　　┃
 * 　　┗━┓　　　┏━┛
 * 　　　　┃　　　┃神兽保佑, 永无BUG!
 * 　　　　┃　　　┃Code is far away from bug with the animal protecting
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　　　┣┓
 * 　　　　┃　　　　　　　┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┃┫┫　┃┫┫
 * 　　　　　┗┻┛　┗┻┛
 * ━━━━━━感觉萌萌哒━━━━━━
 * 本地合并计数统计
 * Author : anduo@qq.com
 * Version: 1.0
 * Date   : 15/10/18
 * time   : 上午10:35
 */
public class ReduceActor extends UntypedActor {

    private ActorRef aggregateActor = null;

    public ReduceActor(ActorRef aggregateActor) {
        this.aggregateActor = aggregateActor;
    }

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof MapData) {
            MapData mapData = (MapData) message;
            ReduceData reduceData = reduce(mapData.getDataList());
            aggregateActor.tell(reduceData);
        } else {
            unhandled(message);
        }
    }

    private ReduceData reduce(List<WordCount> dataList) {
        Map<String, Integer> reduceMap = Maps.newHashMap();
        for (WordCount wordCount : dataList) {
            String word = wordCount.getWord();
            if (reduceMap.containsKey(word)) {
                Integer value = reduceMap.get(word);
                value++;
                reduceMap.put(word, value);
            } else {
                reduceMap.put(word, 1);
            }
        }
        return new ReduceData(reduceMap);
    }
}
