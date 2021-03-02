import scala.io.Source
import java.io.PrintWriter
import java.io.File
import net.liftweb.json.JsonAST._
import net.liftweb.json.JsonDSL._

object main extends App{
  val uData = Source.fromFile("src/main/sources/u.data").getLines()
    .map(_.split("\t"))
    .toArray
    .map { x => (x(2), x(1)) }
    .groupBy(_._1)
    .mapValues(_.length).toSeq
    .sortBy(_._1)
    .map(t => t._2)

  for (line <- uData) {
    println(line)
  }

  val myId = "56"

  val uData2 = Source.fromFile("src/main/sources/u.data").getLines()
    .map(_.split("\t"))
    .toArray
    .filter(x => x(1) == myId)
    .map { x => (x(2), x(1)) }
    .groupBy(_._1)
    .mapValues(_.length).toSeq
    .sortBy(_._1)
    .map(t => t._2)

  for (line <- uData2) {
    println(line)
  }
  println(s"hist_all: ${uData} \n hist_film: ${uData2}")


  // first solution
  val (one, two, three, four, five) = (uData2(0), uData2(1), uData2(2),  uData2(3),  uData2(4))
  val (one_all, two_all, three_all, four_all, five_all) = (uData(0), uData(1), uData(2),  uData(3),  uData(4))
  val jsonOut = s"""{
    "hist_film": [
      $one,
      $two,
      $three,
      $four,
      $five
    ],
    "hist_all": [
      $one_all,
      $two_all,
      $three_all,
      $four_all,
      $five_all
    ]
    }"""

  val writer = new PrintWriter(new File("src/main/lab01.json"))
  writer.write(jsonOut)
  writer.close()

  // second solution
  val jsonAnsw = prettyRender(("hist_film" -> uData2) ~ ("hist_all" -> uData))
  new PrintWriter("src/main/lab01_2.json") {write(jsonAnsw); close}
  }




