package app.tierhaven

import org.springframework.boot.fromApplication
import org.springframework.boot.with


fun main(args: Array<String>) {
	fromApplication<TierhavenApplication>().with(TestcontainersConfiguration::class).run(*args)
}
