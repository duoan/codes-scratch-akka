package com.anduo.learn.akka.scala.actor


import java.util
import akka.actor.Actor
import com.anduo.learn.akka.scala.message.{Result, ReduceData}

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
class AggregateActor extends Actor {
  val finalReducedMap = new util.HashMap[String, Integer]

  def aggregateInMemoryReduce(dataMap: util.HashMap[String, Integer]) = {
    var count: Int = 0
    for (key <- dataMap.keySet()) {
      if (finalReducedMap.containsKey(key)) {
        count = dataMap.get(key)
        count += finalReducedMap.get(key)
        finalReducedMap.put(key, count)
      } else {
        finalReducedMap.put(key, dataMap.get(key))
      }
    }
  }

  def receive: Receive = {
    case message: ReduceData =>
      aggregateInMemoryReduce(message.dataMap)
    case message: Result =>
      println(finalReducedMap)
  }
}
