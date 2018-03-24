organization := "com.birdhowl"
name := "sbt-mfinger"
version := "0.1.0"

// general plugin config
sbtPlugin := true
crossSbtVersions := Seq("0.13.13", "1.0.0")

// publishing config
publishMavenStyle := false
bintrayRepository := "sbt-plugins"
bintrayOrganization in bintray := None
