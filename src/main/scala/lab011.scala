object lab011 {
  import org.apache.spark.sql.SparkSession

  def main(args: Array[String]): Unit = {
    val appName = "lab01"
    val master = "local"

    val spark = SparkSession
      .builder
      .appName(appName)
      .master(master)
      .getOrCreate()
    spark.sparkContext.setLogLevel("ERROR")
    // running func
    val uData = spark.read.textFile("/Users/cernaevaa/IdeaProjects/lab011/src/main/sources/u.data")
    uData.show()
  }





}
