package com.anduo.learn.akka.java;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.anduo.learn.akka.java.actor.MasterActor;
import com.anduo.learn.akka.java.messages.Result;

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
 * Summary: hello akka
 * Author : anduo@qq.com
 * Version: 1.0
 * Date   : 15/10/18
 * time   : 上午10:09
 */
public class HelloAkka {
    public static void main(String[] args) throws Exception {
        ActorSystem system = ActorSystem.create("HelloAkka");
        ActorRef master = system.actorOf(new Props(MasterActor.class), "master");

        master.tell("hi , my name is rocky . I'm so so so so happy to be here .");
        master.tell("Tody , I'm going to read a news article for you .");
        master.tell("I hope I hope you'll like it .");

        Thread.sleep(500);

        master.tell(new Result());
        Thread.sleep(500);
        system.shutdown();
    }
}
