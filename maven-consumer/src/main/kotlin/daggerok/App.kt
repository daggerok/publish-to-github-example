package daggerok

import daggerok.impl.ServiceImpl

/*
// mainClassName: daggerok.AppKt
fun main(args: Array<String>) {
  println("todo")
}
*/

// mainClassName: daggerok.App
class App {
  companion object {
    @JvmStatic fun main(args: Array<String>) {
      val service: Service = ServiceImpl()
      println(service.output)
      println(service.getOutput())
      println(service.getOutput("Max"))
      println(service.getOutput("maksimko"))
    }
  }
}
