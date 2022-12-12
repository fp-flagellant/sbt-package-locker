[![sbt-package-locker Scala version support](https://index.scala-lang.org/fp-flagellant/sbt-package-locker/sbt-package-locker/latest.svg)](https://index.scala-lang.org/fp-flagellant/sbt-package-locker/sbt-package-locker)

## sbt-package-locker
___
sbt-package-locker is a sbt plugin to lock specific lib version, it's aimed to be a part of CI step. 
The goal is simple: Fail if the library version is not supported by organization rules.

## Setup

add as dependency `project/plugins.sbt`:

```
addSbtPlugin("io.github.fp-flagellant" % "sbt-package-locker" % "<version>")
```

## Usage
___
```bash
sbt "checkPackages <MY-RULES-FILE>"
```
`<MY-RULES-FILE>` is a config file described in json 

## Config file example:
___
```json

[
  {
    "organization": "com.typesafe.akka",
    "version": "2.6.20",
    "modules": [
      "akka",
      "akka-actor"
    ]
  }
]
```
where
- `version` is the specific version which will be the latest version you'd like to see in this project
- `modules` check this modules in specified organization
