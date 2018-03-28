import ch.qos.logback.classic.encoder.PatternLayoutEncoder

import java.nio.charset.Charset

import static ch.qos.logback.classic.Level.DEBUG
import static ch.qos.logback.classic.Level.INFO

appender("STDOUT", ConsoleAppender) {
    withJansi = true
    encoder(PatternLayoutEncoder) {
        charset = Charset.forName("UTF-8")
        pattern = $/%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %highlight(%-5level) %C.%M\(%line\) %ex{full, EX_DISPLAY_EVAL} - %msg%n/$
    }
}
appender("FILE", FileAppender) {
    file = "target/slnarch.log"
    encoder(PatternLayoutEncoder) {
        charset = Charset.forName("UTF-8")
        pattern = $/%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %caller{1} %-5level %logger{26}.%M\(%line\) %ex{full, EX_DISPLAY_EVAL} - %msg%n/$
    }
}
logger("com.saha.slnarch", DEBUG)
root(INFO, ["STDOUT", "FILE"])
