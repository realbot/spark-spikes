package com.realbot.spark

import org.apache.spark.{SparkContext, SparkConf}


object WordCountApp {
  def main(args: Array[String]) {
    val logFile = this.getClass.getResource("/README.md").toURI.toString
    val conf = new SparkConf().setAppName("Word Count Application").setMaster("local[2]")
    val sc = new SparkContext(conf)

    val wc = new WordCount(logFile, sc)
    val numAs = wc.count("a")
    val numBs = wc.count("b")

    println("Lines with a: %s, Lines with b: %s".format(numAs, numBs))

    sc.stop()
  }
}
