package pkglocker

import sbt.Def.spaceDelimited
import sbt.Keys._
import sbt._
import sbt.plugins.JvmPlugin

object PackageLocker extends AutoPlugin {
  override def trigger = allRequirements
  override def requires = JvmPlugin

  object autoImport {
    val checkPackages = inputKey[Unit]("Checking packages")
  }

  import autoImport._
  override def projectSettings: Seq[Def.Setting[_]] = Seq(
    checkPackages := {
      val maybePath = spaceDelimited("<arg>").parsed.headOption
      maybePath.fold(throw new MessageOnlyException("Ypu have to specify file name with rules"))(check(_, updateFull.value, streams.value.log))
    }
  )

  override def buildSettings: Seq[Def.Setting[_]] = Seq()
  override def globalSettings: Seq[Def.Setting[_]] = Seq()

  def check(path: String, updateReport: UpdateReport, log: Logger): Unit = {
    log.info("Checking started...")
    val rules = RuleExtractor.getData(path)
    rules match {
      case Left(value) =>
        log.err(s"Error found in .package-lock file, reason: ${value.getMessage}")
      case Right(value) =>
        val rules = value.groupBy(_.organization)
        val modules = updateReport.allModules
        val violationsList = modules.foldLeft(Seq.empty[String]){
          case (aggr, module) =>
            rules.get(module.organization).flatMap(organizationRules => {
              val name = module.name.split('_').head
              organizationRules.find(_.modules.contains(name)).flatMap(rule => {
                if(VersionNumber.apply(module.revision).matchesSemVer(SemanticSelector(s">${rule.version}"))) {
                  Some(s"Version ${module.revision} in module ${name} is not supported. Latest supported version is ${rule.version}")
                } else None
              })
            }).fold(aggr)(_ +:aggr)
        }
        if(violationsList.nonEmpty)
          throw new MessageOnlyException(s"Rules violations:\n${violationsList.mkString("\n")}")
    }
  }
}