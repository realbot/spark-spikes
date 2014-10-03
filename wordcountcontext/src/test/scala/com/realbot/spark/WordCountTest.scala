package com.realbot.spark

import org.specs2.mutable._

class WordCountTest extends Specification with WithSpark {

  "word count" should {
    "count a and b" in {
      val logFile = this.getClass.getResource("/README.md").toURI.toString
      val wc: WordCount = new WordCount(logFile, sc)

      wc.count("a") === 73
      wc.count("b") === 35
    }

    "count words" in {
      val logFile = this.getClass.getResource("/README.md").toURI.toString
      val wc: WordCount = new WordCount(logFile, sc)

      wc.words(5).toList must contain(exactly(("", 149), ("the", 28), ("to", 19), ("Spark", 12), ("Hadoop", 11)))
    }
  }

}