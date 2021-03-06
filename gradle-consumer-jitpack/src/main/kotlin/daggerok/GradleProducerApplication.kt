package daggerok

import com.samskivert.mustache.Mustache
import daggerok.impl.ServiceImpl
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.mustache.MustacheResourceTemplateLoader
import org.springframework.boot.runApplication
import org.springframework.boot.web.reactive.result.view.MustacheViewResolver
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import reactor.core.publisher.Mono

@Controller
class IndexCtrl {

  @GetMapping
  fun get(model: Model): Mono<String> {
    val service: Service = ServiceImpl()
    model.addAttribute("message", service.getOutput("Maksimko"))
    return Mono.just("index")
  }
}

@SpringBootApplication
class GradleProducerApplication {

  @Bean
  fun viewResolver(): MustacheViewResolver {
    val prefix = "classpath:/templates/"
    val suffix = ".hbs"
    val loader = MustacheResourceTemplateLoader(prefix, suffix)
    return MustacheViewResolver(Mustache.compiler().withLoader(loader)).apply {
      setPrefix(prefix)
      setSuffix(suffix)
    }
  }
}

fun main(args: Array<String>) {
  runApplication<GradleProducerApplication>(*args)
}
