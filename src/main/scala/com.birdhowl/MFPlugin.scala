package com.birdhowl

import sbt._
import sbt.Keys._

object MFPlugin extends AutoPlugin {
  override def requires = sbt.plugins.JvmPlugin
  override def trigger = allRequirements

  object autoImport {
    lazy val mfFinger = taskKey[String]("Text to show when compilation fails")
  }
  import autoImport._

  override val projectSettings = Seq(
    mfFinger := """|...................../´¯¯/)
                   |...................,/¯.../
                   |.................../..../
                   |.............../´¯/'..'/´¯¯`·¸
                   |.........../'/.../..../....../¨¯\
                   |..........('(....´...´... ¯~/'..')
                   |...........\..............'...../
                   |............\....\.........._.·´
                   |.............\..............(
                   |..............\..............\""".stripMargin) ++
    mfFingerSettings(Compile) ++
    mfFingerSettings(Test)

  private def mfFingerSettings(conf: Configuration) = Seq(
    compile in conf := {
      (compile in conf).result.value match {
        case Inc(ex: Incomplete) =>
          val fingerText = mfFinger.value
          val fingerWidth = fingerText.split("\n").map(_.size).max
          val theFinger = mfFinger.value + "\n" +
            footer(name.value, fingerWidth)

          sLog.value.error(theFinger)

          throw ex
        case Value(v) => v
      }
    })

  def footer(name: String, minWidth: Int) = {
    val footerWidth = Math.max(minWidth, name.size + 4)
    val line = Seq.fill(footerWidth - 2)('-').mkString

    val paddedName = {
      val nameWidth = footerWidth - 2
      val neededPadding = nameWidth - name.size
      val leftPad = neededPadding / 2
      val rightPad = neededPadding - leftPad

      Seq.fill(leftPad)(' ').mkString + name + Seq.fill(rightPad)(' ').mkString
    }

    s"""|+$line+
        ||$paddedName|
        |+$line+""".stripMargin
  }
}
