package sorting

class Data(serviceName: String = "", price: Int = 0, deadline: Int = 0, subprice: Int = 0)  {

  def <(that: Data): Boolean = {
    if (this.serviceName < that.serviceName) {
      true
    } else if (this.serviceName == that.serviceName) {
      if (this.price < that.price) {
        true
      } else if (this.price == that.price) {
        if (this.deadline < that.deadline) {
          true
        } else if (this.deadline == that.deadline) {
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
    } else {
      false
    }
  }

  def <=(that: Data): Boolean = {
    (this < that) || (this.serviceName == that.serviceName && this.price == that.price &&
      this.deadline == that.deadline && this.subprice == that.subprice)
  }

  def >(that: Data): Boolean = {
    !(this <= that)
  }

  def >=(that: Data): Boolean = {
    !(this < that)
  }

}
