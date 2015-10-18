package com.anduo.learn.akka.scala

import akka.actor.{Props, ActorSystem}
import com.anduo.learn.akka.scala.actor.MasterActor
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
 * time   : 上午11:55
 */
object MRApplication {

  def main(args: Array[String]) {
    val _system = ActorSystem("wordcount-akka")
    val master = _system.actorOf(Props[MasterActor], name = "master")
    master ! "Hi ! my name is rocky . i am so so so happy to be here ."
    master ! "2 yews ! my name is rocky . i am so so so happy to be here ."
    master ! " 3 Hi ! my name is rocky . i am so so so happy to be here ."
    master ! "4 Hi ! my name is rocky . i am so so so happy to be here ."

    Thread.sleep(2000)
    master ! new Result
    Thread.sleep(2000)
    _system.terminate
  }

}
