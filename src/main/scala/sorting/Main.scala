package sorting

import au.com.bytecode.opencsv.CSVWriter

import scala.io.BufferedSource
import java.io._
import java.io.{BufferedWriter, FileWriter}
import scala.collection.mutable.ListBuffer
import scala.jdk.CollectionConverters._
import scala.util.Random
import au.com.bytecode.opencsv.CSVWriter

import scala.io.BufferedSource

object Main {

  def printToFile(f: java.io.File)(op: java.io.PrintWriter => Unit): Unit = {
    val p = new java.io.PrintWriter(f)
    try {
      op(p)
    } finally {
      p.close()
    }
  }

  def main(args: Array[String]): Unit = {

    var time_bubble_arr: Map[String, String] = Map.empty[String, String]
    var time_insertion_arr: Map[String, String] = Map.empty[String, String]
    var time_shaker_arr: Map[String, String] = Map.empty[String, String]

    val selections: Array[String] = Array[String]("100", "500", "1000", "2500", "5000", "10000", "25000", "50000", "100000")
    var data_arr: Array[Data] = Array[Data]()

    utils.data.MakeCSV.generateData(selections)

    for (selection <- selections) {
      val data_source: BufferedSource = io.Source.fromFile(s"src/main/resources/$selection.csv")
      for (line <- data_source.getLines.drop(1)) {
        val splittedLine: Array[String] = line.split(",")
        val DataObject: Data = new DataClass(splittedLine(0), splittedLine(1), splittedLine(2), splittedLine(3))
        data_arr = data_arr :+ DataObject
      }
      data_source.close

      val outputFile1: BufferedWriter = new BufferedWriter(new FileWriter(s"src/main/resources/sorted/bubble_$selection.csv"))
      val outputFile2: BufferedWriter = new BufferedWriter(new FileWriter(s"src/main/resources/sorted/insertion_$selection.csv"))
      val outputFile3: BufferedWriter = new BufferedWriter(new FileWriter(s"src/main/resources/sorted/shaker_$selection.csv"))
      val csvWriter1: CSVWriter = new CSVWriter(outputFile1)
      val csvWriter2: CSVWriter = new CSVWriter(outputFile2)
      val csvWriter3: CSVWriter = new CSVWriter(outputFile3)


      val starttime_bubble = System.currentTimeMillis()
      val bubble_arr: Array[Data] = utils.sort.Sort.bubble_sort(data_arr)
      val endtime_bubble = System.currentTimeMillis()
      val time_bubble: String = (endtime_bubble - starttime_bubble).toString
      time_bubble_arr += (selection -> s"$time_bubble ms")


      var bubble = new ListBuffer[Array[String]]
      for (elem <- bubble_arr) {
        bubble += Array(elem.serviceName, elem.deadline, elem.price, elem.subprice)
      }
      csvWriter1.writeAll(bubble.toList.asJava)


      val starttime_insertion = System.currentTimeMillis()
      val insertion_arr: Array[Data] = utils.sort.Sort.insertion_sort(data_arr)
      val endtime_insertion = System.currentTimeMillis()
      val time_insertion: String = (endtime_insertion - starttime_insertion).toString
      time_insertion_arr += (selection -> s"$time_insertion ms")


      var insertion = new ListBuffer[Array[String]]
      for (elem <- insertion_arr) {
        insertion += Array(elem.serviceName, elem.deadline, elem.price, elem.subprice)
      }
      csvWriter2.writeAll(insertion.toList.asJava)


      val starttime_shaker = System.currentTimeMillis()
      val shaker_arr: Array[Data] = utils.sort.Sort.shaker_sort(data_arr)
      val endtime_shaker = System.currentTimeMillis()
      val time_shaker: String = (endtime_shaker - starttime_shaker).toString
      time_shaker_arr += (selection -> s"$time_shaker ms")


      var shaker = new ListBuffer[Array[String]]
      for (elem <- shaker_arr) {
        shaker += Array(elem.serviceName, elem.deadline, elem.price, elem.subprice)
      }
      csvWriter3.writeAll(shaker.toList.asJava)
    }

    println("bubble")
    for (elem <- time_bubble_arr) {
      println(elem)
    }
    print('\n')
    print('\n')

    println("insertion")
    for (elem <- time_insertion_arr) {
      println(elem)
    }
    print('\n')
    print('\n')

    println("shaker")
    for (elem <- time_shaker_arr) {
      println(elem)
    }

  }
}
