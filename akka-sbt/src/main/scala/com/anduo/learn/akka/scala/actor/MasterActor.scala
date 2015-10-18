package com.anduo.learn.akka.scala.actor

import akka.actor.{Props, ActorRef, Actor}
import com.anduo.learn.akka.scala.message.Result

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
 * Summary: TODO
 * Author : anduo@qq.com
 * Version: 1.0
 * Date   : 15/10/18
 * time   : 上午11:57
 */
class MasterActor extends Actor {

  var aggregateActor: ActorRef = context.actorOf(Props[AggregateActor], name = "aggregate")
  var reduceActor: ActorRef = context.actorOf(Props(new ReduceActor(aggregateActor)), name = "reduce")
  var mapActor: ActorRef = context.actorOf(Props(new MapActor(reduceActor)), name = "map")

  def receive: Receive = {
    case message: String => mapActor ! message
    case message: Result => aggregateActor ! message
    case _ =>
  }
}
