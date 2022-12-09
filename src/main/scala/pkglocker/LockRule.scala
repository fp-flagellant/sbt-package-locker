package pkglocker

import io.circe.Decoder
import io.circe.generic.semiauto._

final case class LockRule(
  organization: String,
  version: String,
  modules: Seq[String]
)

object LockRule {
  implicit val lockRuleDecoder: Decoder[LockRule] = deriveDecoder[LockRule]
}
