package com.anduo.learn.akka.java.actor;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import com.anduo.learn.akka.java.messages.Result;
import scala.ScalaObject;

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
 * Summary: actor控制器
 * Author : anduo@qq.com
 * Version: 1.0
 * Date   : 15/10/18
 * time   : 上午10:19
 */
public class MasterActor extends UntypedActor {

    // 通过上线文来调用actor，不能直接new出来，使用了akka的上线文管理actor
    // actor的句柄引用
    private ActorRef aggregateActor = getContext().actorOf(new Props(() -> {
        return new AggregateActor();
    }), "aggregate");

    private ActorRef reduceActor = getContext().actorOf(new Props(() -> {
        return new ReduceActor(aggregateActor);
    }), "reduce");

    private ActorRef mapActor = getContext().actorOf(new Props(() -> {
        return new MapActor(reduceActor);
    }), "map");

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof String) {
            mapActor.tell(message);
        } else if (message instanceof Result) {
            aggregateActor.tell(message);
        } else {
            unhandled(message);
        }
    }
}
