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
      // A[ i ] is added in the sorted sequence A[0, .. i-1]
      // save A[i] to make a hole at index iHole
      val item = arr(i)
      var iHole = i
      // keep moving the hole to next smaller index until A[iHole - 1] is <= item
      while (iHole > 0 && arr(iHole - 1) > item) {
        // move hole to next smaller index
        arr(iHole) = arr(iHole - 1)
        iHole = iHole - 1
      }
      // put item in the hole
      arr(iHole) = item
    }
    arr
  }

  def shaker_sort(arr: Array[Data]): Array[Data] = {
    var swapped = true
    var start = 0
    var end = a.length

    while ( {
      swapped == true
    }) { // reset the swapped flag on entering the
      // loop, because it might be true from a
      // previous iteration.
      swapped = false
      // loop from bottom to top same as
      // the bubble sort
      for (i <- start until end - 1) {
        if (a(i) > a(i + 1)) {
          val temp = a(i)
          a(i) = a(i + 1)
          a(i + 1) = temp
          swapped = true
        }
      }
      // if nothing moved, then array is sorted.
      if (swapped == false) break //todo: break is not supported
      // otherwise, reset the swapped flag so that it
      // can be used in the next stage
      swapped = false
      // move the end point back by one, because
      // item at the end is in its rightful spot
      end = end - 1
      // from top to bottom, doing the
      // same comparison as in the previous stage
      for (i <- end - 1 to start by -1) {
        if (a(i) > a(i + 1)) {
          val temp = a(i)
          a(i) = a(i + 1)
          a(i + 1) = temp
          swapped = true
        }
      }
      // increase the starting point, because
      // the last stage would have moved the next
      // smallest number to its rightful spot.
      start = start + 1
    }
  }

}
