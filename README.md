# sbt-mfinger

Render a middle-finger in ASCII whenever your code fails to compile.

## Usage

Clone this project and run:

```shell
sbt ^publishLocal
```

And then add this to your project or global plugins file:
```scala
addSbtPlugin("com.birdhowl" % "sbt-mfinger" % "0.1.0-SNAPSHOT")
```

Then, whenever your project fails to compile, it will show you its true intentions:
```
[info] Compiling 1 Scala source to /Users/bhowell/expir/mf-plugin/target/scala-2.12/sbt-1.0/classes ...
[error] /Users/bhowell/expir/mf-plugin/src/main/scala/com.birdhowl/MFPlugin.scala:52:46: value foo is not a member of Int
[error]       val rightPad = neededPadding - leftPad foo
[error]                                              ^
[error] one error found
[error] ...................../´¯¯/)
[error] ...................,/¯.../
[error] .................../..../
[error] .............../´¯/'..'/´¯¯`·¸
[error] .........../'/.../..../....../¨¯\
[error] ..........('(....´...´... ¯~/'..')
[error] ...........\..............'...../
[error] ............\....\.........._.·´
[error] .............\..............(
[error] ..............\..............\
[error] +--------------------------------+
[error] |          sbt-mfinger           |
[error] +--------------------------------+
```

## Configuration

If you want to change the art for the mfinger text, you can easily do so:

```scala
mfFinger := """|+----------------------------------+
               || Annoying people with no class at |
               || all should stay far away from    |
               || computers!                       |""".stripMargin
```

Since it's a task, you can even take text from a shell command:

```scala
import scala.sys.process._
mfFinger := "cowsay -d Wow, you suck".!!
```

## License

Published under the [WTFPL](http://www.wtfpl.net/txt/copying/).
