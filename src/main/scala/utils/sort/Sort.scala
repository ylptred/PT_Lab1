package utils.sort

import sorting.Data

object Sort {

  def bubble_sort(arr: Array[Data]): Array[Data] = {
    for (i <- 0 until arr.length - 1; j <- 0 until arr.length - 1 - i) {
      if (arr(j) > arr(j + 1)) {
        val temp = arr(j)
        arr(j) = arr(j + 1)
        arr(j + 1) = temp
      }
    }
    arr
  }

  def insertion_sort(arr: Array[Data]): Array[Data] = {
    for (i <- 1 until arr.length) {
      val item = arr(i)
      var iHole = i
      while (iHole > 0 && arr(iHole - 1) > item) {
        arr(iHole) = arr(iHole - 1)
        iHole = iHole - 1
      }
      arr(iHole) = item
    }
    arr
  }

  def shaker_sort(arr: Array[Data]): Array[Data] = {
    var swapped = false
    do {
      def swap(i: Int): Unit = {
        val temp = arr(i)
        arr(i) = arr(i + 1)
        arr(i + 1) = temp
        swapped = true
      }

      swapped = false
      for (i <- 0 to (arr.length - 2)) if (arr(i) > arr(i + 1)) swap(i)

      if (swapped) {
        swapped = false
        for (j <- arr.length - 2 to 0 by -1) if (arr(j) > arr(j + 1)) swap(j)
      }
    } while (swapped)
    arr
  }

}
