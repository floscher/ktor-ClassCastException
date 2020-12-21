import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import java.net.URL

class TestMain {

  companion object {

    val testSequence = 1000..8000

    @BeforeAll
    @JvmStatic
    fun before() {
      startServer()

      testSequence.forEach { n ->
        addContent("$n", "a".repeat(n))
      }
    }
  }

  @Test
  fun testWriter() {
    testSequence.forEach { n ->
      assertEquals("a".repeat(n), URL("http://[::]:$PORT/writer/$n").readText()) { "$n Bytes FAILED" }
      println("$n Bytes OK")
    }
  }

  @Test
  fun testText() {
    testSequence.forEach { n ->
      assertEquals("a".repeat(n), URL("http://[::]:$PORT/text/$n").readText()) { "$n Bytes FAILED" }
      println("$n Bytes OK")
    }
  }
}