package sorting

import scala.io.BufferedSource

import java.io._

object Working extends App {

  def printToFile(f: java.io.File)(op: java.io.PrintWriter => Unit) {
    val p = new java.io.PrintWriter(f)
    try {
      op(p)
    } finally {
      p.close()
    }
  }

  val selections: Array[String] = Array[String]("100", "500", "1000", "2500", "5000", "10000", "25000", "50000", "100000")
  var data_arr: Array[Data] = Array[Data]()

  utils.data.MakeCSV.generateData(selections)

  for (selection <- selections) {
    val data_source: BufferedSource = io.Source.fromFile(s"src/main/resources/$selection.csv")
    for (line <- data_source.getLines.drop(1)) {
      val splittedLine: Array[String] = line.split(",")
      val DataObject: Data = new Data(splittedLine(0), splittedLine(1).toInt, splittedLine(2).toInt, splittedLine(3).toInt)
      data_arr = data_arr :+ DataObject
    }
    data_source.close
  }

  val bubble_arr: Array[Data] = utils.sort.Sort.bubble_sort(data_arr)
  printToFile(new File("src/main/resources/sorted/bubble_sort.txt")) {
    p => bubble_arr.foreach(println)
  }

  val insertion_arr: Array[Data] = utils.sort.Sort.insertion_sort(data_arr)
  printToFile(new File("src/main/resources/sorted/insertion_sort.txt")) {
    p => insertion_arr.foreach(println)
  }

  val shaker_arr: Array[Data] = utils.sort.Sort.shaker_sort(data_arr)
  printToFile(new File("src/main/resources/sorted/shaker_sort.txt")) {
    p => shaker_arr.foreach(println)
  }

}
