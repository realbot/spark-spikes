
object WordCountApp {
  def main(args: Array[String]) {
    val logFile = this.getClass.getResource("/README.md").toURI.toString

    val wc = new WordCount(logFile, "local")
    val numAs = wc.count("a")
    val numBs = wc.count("b")

    println("Lines with a: %s, Lines with b: %s".format(numAs, numBs))

    wc.shutdown()
  }
}
