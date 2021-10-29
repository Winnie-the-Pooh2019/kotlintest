package userdata

import java.security.MessageDigest
import kotlin.random.Random

object Encoder {
    fun encode(source: String): String {
        return MessageDigest
            .getInstance("MD5")
            .digest(source.toByteArray()).toHex()
    }

    fun saltGen(): String = Random.nextBytes(32).toHex()

    private fun ByteArray.toHex(): String = joinToString("") { "%02x".format(it) }
}