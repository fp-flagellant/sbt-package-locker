## This project is aimed to be a part of CI step.

## All you need is &#9829; this plugin added to the project

```
addSbtPlugin("fp-flagellant" % "sbt-package-locker" % "<version>")
```

and list of rules

```json

[
  {
    "organization": "org.jline",
    "version": "3.0.0",
    "modules": [
      "jline"
    ]
  },
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

- `version` is the specific version which will be the latest version you'd like to see in this project
- `modules` check this modules in specified organization

In the sbt:
```sbt
checkPackages <MY-RULES-FILE>
```