package sorting

import scala.io.BufferedSource

object Working extends App {
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


}
