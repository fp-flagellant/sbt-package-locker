package pkglocker

import io.circe.jawn.decode

import scala.io.Source

object RuleExtractor {

  def getData(path: String) = {
    val src = Source.fromFile(path)
    val rawJsonString = src.getLines().mkString
    src.close()
    decode[Vector[LockRule]](rawJsonString)
  }

}