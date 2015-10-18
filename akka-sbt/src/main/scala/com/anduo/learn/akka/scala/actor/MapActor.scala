package com.anduo.learn.akka.scala.actor

import java.util.ArrayList
import java.util.StringTokenizer

import akka.actor.{ActorRef, Actor}
import com.anduo.learn.akka.scala.message.{WordCount, MapData}

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
 * time   : 下午12:25
 */
class MapActor(reduceActor: ActorRef) extends Actor {
  val STOP_WORDS_LIST = List("a", ",", ".", "is", "be")

  def evaluateExpression(line: String): MapData = {
    val dataList = new ArrayList[WordCount]
    val parser: StringTokenizer = new StringTokenizer(line)
    val defaultCount: Integer = 1;
    while (parser.hasMoreTokens) {
      val word: String = parser.nextToken().toLowerCase
      if (!STOP_WORDS_LIST.contains(word)) {
        dataList.add(new WordCount(word, defaultCount))
      }
    }
    return new MapData(dataList)
  }

  def receive: Receive = {
    case message: String => reduceActor ! evaluateExpression(message)
  }
}
