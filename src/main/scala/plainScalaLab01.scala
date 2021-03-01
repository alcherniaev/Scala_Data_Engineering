import scala.io.Source
import java.io.PrintWriter
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

  val myId = "191"

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

  val jsonAnswer = prettyRender(("hist_film" -> uData) ~ ("hist_all" -> uData2))
  new PrintWriter("src/main/scala/data/lab01.json") { write(jsonAnswer); close}

}
