import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.core.config.ConfigurationSource
import org.apache.logging.log4j.core.config.Configurator
import java.io.BufferedInputStream
import java.io.File
import java.io.FileInputStream

fun main() {
    val file = File("resources/log4j2.xml")
    println(file.absolutePath)
    println(file.exists())
    val inn = BufferedInputStream(FileInputStream(file))
    val source = ConfigurationSource(inn)
    Configurator.initialize(null, source)

    val logger = LogManager.getRootLogger()


}