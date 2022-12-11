package pkglocker

import zio.json._

final case class LockRule(
  organization: String,
  version: String,
  modules: Seq[String]
)

object LockRule {

  implicit val lockRuleDecoder: JsonDecoder[LockRule] = DeriveJsonDecoder.gen[LockRule]
}
