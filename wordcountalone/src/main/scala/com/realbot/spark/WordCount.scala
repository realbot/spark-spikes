package com.realbot.spark

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.SparkContext._

class WordCount(filename: String, master: String) {
  private val conf = new SparkConf().setAppName("Word Count Application").setMaster(master)
  private val sc = new SparkContext(conf)
  private val lines = sc.textFile(filename, 2).cache()

  def count(word: String): Long = {
      lines.filter(line => line.contains(word)).count()
  }

  def words(wordNum: Int): Array[(String, Int)] = {
    val wordCounts = lines.flatMap(line => line.split(" ")).map(word => (word, 1)).reduceByKey((a, b) => a + b)
    wordCounts.takeOrdered(wordNum)(Ordering.by[(String, Int), Int](_._2).reverse)
  }

  def shutdown() = {
    sc.stop()
  }

}
