import org.apache.spark.sql.SparkSession


object filter {
  def main(args: Array[String]) = {
    val spark = SparkSession
      .builder()
      .appName("lab04a")
      .getOrCreate()
    import spark.implicits._

    val topicName = spark.conf.get("spark.filter.topic_name")
    var offsetKafka = spark.conf.get("spark.filter.offset")
    val dir = spark.conf.get("spark.filter.output_dir_prefix")

    if (offsetKafka != "earliest") {
      offsetKafka = s"""{"${topic}":{"0":${offsetKafka}}}"""
    }

    var df = spark.read
      .format("kafka")
      .option("kafka.bootstrap.servers", "10.0.1.13:6667")
      .option("subscribe", topicName)
      .option("startingOffset", offsetKafka)
      .option("endingOffsets", "latest")
      .load
  }


}
