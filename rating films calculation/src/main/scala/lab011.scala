import org.apache.spark.rdd.RDD

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
    val uData  = spark.read.textFile("/Users/cernaevaa/IdeaProjects/lab011/src/main/sources/u.data").rdd

    //uData.show()
    //uData.map(x => x.split(" "))
    import spark.implicits._
    // uData.map(x => x.split("")).take(10).toDF()
    // val u_data_spl: RDD[String] = uData.map(x => x.split(""))
    //u_data_spl.take(5)
    import spark.sqlContext.implicits._

    //spark.createDataFrame(uData.map(x => x.split("")).take(10))
  }





}
