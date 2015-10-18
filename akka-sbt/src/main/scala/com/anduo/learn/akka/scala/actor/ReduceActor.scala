package com.anduo.learn.akka.scala.actor

import java.util.ArrayList
import java.util.HashMap

import akka.actor.{ActorRef, Actor}
import com.anduo.learn.akka.scala.message.{MapData, WordCount, ReduceData, Result}
import scala.collection.JavaConversions._

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
 * time   : 下午12:01
 */
class ReduceActor(aggregateActor: ActorRef) extends Actor {

  def reduce(dataList: ArrayList[WordCount]): ReduceData = {
    val reduceMap = new HashMap[String, Integer]
    for (wc: WordCount <- dataList) {
      val word: String = wc.word;
      if (reduceMap.containsKey(word)) {
        reduceMap.put(word, reduceMap.get(word) + 1)
      } else {
        reduceMap.put(word, 1)
      }
    }
    return new ReduceData(reduceMap)
  }

  def receive: Receive = {
    case message: MapData =>
      aggregateActor ! reduce(message.dataList)
    case _ =>
  }
}
