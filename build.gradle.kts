import org.gradle.api.tasks.testing.logging.TestLogEvent

plugins {
  kotlin("jvm").version("1.4.21")
}

repositories {
  jcenter()
}

tasks.withType(Test::class) {
  useJUnitPlatform()
  testLogging {
    events(TestLogEvent.STANDARD_OUT, TestLogEvent.FAILED, TestLogEvent.PASSED, TestLogEvent.STANDARD_ERROR, TestLogEvent.STARTED)
  }
}

dependencies {
  implementation("io.ktor:ktor-server-core:1.5.0")
  implementation("io.ktor:ktor-server-netty:1.5.0")
  implementation("org.junit.jupiter:junit-jupiter:5.7.0")
}
