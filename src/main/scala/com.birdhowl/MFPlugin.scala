package com.birdhowl

import sbt._
import sbt.Keys._

object MFPlugin extends AutoPlugin {
  override def requires = sbt.plugins.JvmPlugin
  override def trigger = allRequirements

  object autoImport {
    lazy val mfFinger = settingKey[String]("Text to show when compilation fails")
  }
  import autoImport._

  override val projectSettings =
    inConfig(Compile)(mfFingerSettings) ++
      inConfig(Test)(mfFingerSettings)

  private lazy val mfFingerSettings = Seq(
    compile := {
      compile.result.value match {
        case Inc(ex: Incomplete) =>
          val fingerWidth = mfFinger.value.split("\n").map(_.size).max
          val theFinger = mfFinger.value + "\n" +
            footer(name.value, fingerWidth)

          sLog.value.error(theFinger)

          throw ex
        case Value(v) => v
      }
    },
    mfFinger := """|...................../´¯¯/)
                   |...................,/¯.../
                   |.................../..../
                   |.............../´¯/'..'/´¯¯`·¸
                   |.........../'/.../..../....../¨¯\
                   |..........('(....´...´... ¯~/'..')
                   |...........\..............'...../
                   |............\....\.........._.·´
                   |.............\..............(
                   |..............\..............\""".stripMargin)

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
