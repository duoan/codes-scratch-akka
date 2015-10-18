package com.anduo.learn.akka.java.actor;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import com.anduo.learn.akka.java.messages.MapData;
import com.anduo.learn.akka.java.messages.WordCount;
import com.google.common.collect.Lists;
import scala.ScalaObject;

import java.util.List;
import java.util.StringTokenizer;

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
 * Summary: 分词
 * Author : anduo@qq.com
 * Version: 1.0
 * Date   : 15/10/18
 * time   : 上午10:21
 */
public class MapActor extends UntypedActor {

    private final List<String> STOP_WORDS_LIST = Lists.newArrayList(",",".","is","a","be","for","hi","my");

    private ActorRef reduceActor;

    public MapActor(ActorRef reduceActor) {
        this.reduceActor = reduceActor;
    }

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof String) {
            String msgString = (String) message;
            MapData date = evaluateExpression(msgString);
            reduceActor.tell(date);
        } else {
            unhandled(message);
        }
    }

    private MapData evaluateExpression(String line) {
        List<WordCount> dataList = Lists.newArrayList();
        StringTokenizer parser = new StringTokenizer(line);
        while (parser.hasMoreElements()) {
            String word = parser.nextToken().toLowerCase();
            if (!STOP_WORDS_LIST.contains(word)) {
                dataList.add(new WordCount(word, 1));
            }
        }
        return new MapData(dataList);
    }
}
