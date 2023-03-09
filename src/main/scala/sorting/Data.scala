package sorting

trait Data {

  val serviceName: String
  val price: Int
  val deadline: Int
  val subprice: Int

  def <(that: Data): Boolean = {
    if (this.serviceName < that.serviceName) {
      true
    } else if (this.serviceName == that.serviceName) {
      if (this.price < that.price) {
        true
      } else if (this.price == that.price) {
        if (this.subprice < that.subprice) {
          true
        } else {
          false
        }
      } else {
        false
      }
    } else {
      false
    }
  }

  def <=(that: Data): Boolean = {
    (this < that) || (this.serviceName == that.serviceName && this.price == that.price && this.subprice == that.subprice)
  }

  def >(that: Data): Boolean = {
    !(this <= that)
  }

  def >=(that: Data): Boolean = {
    !(this < that)
  }
}

class DataClass(val serviceName: String, val price: Int, val deadline: Int, val subprice: Int) extends Data
