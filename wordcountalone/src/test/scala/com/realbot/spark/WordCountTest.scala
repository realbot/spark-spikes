package com.realbot.spark

import org.apache.log4j.{Level, Logger}
import org.specs2.mutable._

class WordCountTest extends Specification {

  var wc: WordCount = _

  step {
    silenceLogs(Level.WARN, Seq("org.apache.spark", "org.eclipse.jetty", "akka"))
    val logFile = this.getClass.getResource("/README.md").toURI.toString
    wc = new WordCount(logFile, "local[4]")
  }

  "word count" should {
    "count a and b" in {
      wc.count("a") === 73
      wc.count("b") === 35
    }

    "count words" in {
      wc.words(5).toList must contain(exactly(("", 149), ("the", 28), ("to", 19), ("Spark", 12), ("Hadoop", 11)))
    }
  }

  step {
    wc.shutdown()
  }

  private def silenceLogs(level: Level, loggers: TraversableOnce[String]) = {
    loggers.foreach { name => Logger.getLogger(name).setLevel(level) }
  }
}