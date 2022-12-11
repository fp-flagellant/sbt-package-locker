package pkglocker

import zio.json.DecoderOps

import scala.io.Source

object RuleExtractor {

  def getData(path: String) = {
    val src = Source.fromFile(path)
    val rawJsonString = src.getLines().mkString
    src.close()

    rawJsonString.fromJson[Vector[LockRule]]
  }

}