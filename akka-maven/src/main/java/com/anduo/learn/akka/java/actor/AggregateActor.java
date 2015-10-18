package com.anduo.learn.akka.java.actor;

import akka.actor.UntypedActor;
import com.anduo.learn.akka.java.messages.ReduceData;
import com.anduo.learn.akka.java.messages.Result;
import com.google.common.collect.Maps;

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
 * 集中合并结果统计
 * Author : anduo@qq.com
 * Version: 1.0
 * Date   : 15/10/18
 * time   : 上午10:42
 */
public class AggregateActor extends UntypedActor {
    private Map<String, Integer> finalReducedMap = Maps.newHashMap();

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof ReduceData) {
            ReduceData reduceData = (ReduceData) message;
            aggregateInMemoryReduce(reduceData.getReduceDataList());
        } else if (message instanceof Result) {
            System.out.println(finalReducedMap.toString());
        }
    }

    private void aggregateInMemoryReduce(Map<String, Integer> reduceDataList) {
        Integer count = null;
        for (String key : reduceDataList.keySet()) {
            if (finalReducedMap.containsKey(key)) {
                count = reduceDataList.get(key) + finalReducedMap.get(key);
                finalReducedMap.put(key, count);
            } else {
                finalReducedMap.put(key, reduceDataList.get(key));
            }
        }
    }
}
