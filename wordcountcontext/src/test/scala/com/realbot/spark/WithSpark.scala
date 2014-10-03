package com.realbot.spark

import org.apache.log4j.{Logger, Level}
import org.apache.spark.{SparkContext, SparkConf}
import org.specs2.mutable._
import org.specs2.specification.{Fragments, Step}

trait WithSpark extends Specification {
  lazy val sc = createContext

  override def map(fragments: =>Fragments) =
    fragments ^ Step(destroyContext)

  private def createContext(): SparkContext = {
    silenceLogs(Level.WARN, Seq("org.apache.spark", "org.eclipse.jetty", "akka"))

    val conf = new SparkConf().setAppName("Spark Test").setMaster("local[2]")
    new SparkContext(conf)
  }

  private def destroyContext() {
    sc.stop()
  }

  private def silenceLogs(level: Level, loggers: TraversableOnce[String]) = {
    loggers.foreach { name => Logger.getLogger(name).setLevel(level) }
  }
}