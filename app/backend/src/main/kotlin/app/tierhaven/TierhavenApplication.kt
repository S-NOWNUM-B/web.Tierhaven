package app.tierhaven

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TierhavenApplication

fun main(args: Array<String>) {
    runApplication<TierhavenApplication>(*args)
}
