import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.HttpStatusCode.Companion
import io.ktor.request.path
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.response.respondTextWriter
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty


const val PORT = 1234

fun startServer() {
  embeddedServer(
    Netty,
    port = PORT,
    module = Application::serve
  ).start(false)
}

public fun addContent(param: String, newContent: String) {
  content[param] = newContent
}

private val content: MutableMap<String, String> = mutableMapOf()

fun Application.serve() {
  routing {
    get("/writer/{param...}") {
      content[call.parameters["param"]]?.let { content ->
        call.respondTextWriter(ContentType("text", "plain"), HttpStatusCode.OK) {
          write(content)
        }
      } ?: call.respond(HttpStatusCode.NotFound, "Not Found")
    }
    get("/text/{param...}") {
      content[call.parameters["param"]]?.let { content ->
        call.respondText(ContentType("text", "plain"), HttpStatusCode.OK) {
          content
        }
      } ?: call.respond(HttpStatusCode.NotFound, "Not Found")
    }
  }
}