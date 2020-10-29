package moe.liar.model

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import moe.liar.utils.Option
import moe.liar.utils.getOrElse
import moe.liar.utils.none
import moe.liar.utils.some
import java.io.File
import kotlin.random.Random

interface Resources {
    abstract fun uri(): String
}

class Link (private val link: String): Resources {
    override fun uri(): String = link
}

class Static(rootPath: String) {
    private val root: String = if(rootPath.last() == '/') rootPath else "$rootPath/"

    fun path(name: String) = StaticPath(root, name)

    class StaticPath(private val root: String, private val name: String) : Resources {
        override fun uri(): String = "$root$name"
    }
}

val ImgRes = Static("/static/img/")
val JsRes = Static("/static/js/")
val CssRes = Static("/static/css/")
val IconRes = Static("/static/img/icon/")
val IconSvgRes = Static("/static/img/icon/svg/")
val BackgroundRes = Static("/static/img/background/")

object RandomImgAPI {
    fun link(size: Int = 1) =  Link("https://random.52ecy.cn/randbg.php/${Random.nextInt()}?size=$size")
}

object RandomBackground {
    private const val backgroundDir = "./resources/static/img/background/"
    private var cache: List<Resources> = listOf()
    suspend fun precache() = withContext(Dispatchers.IO) {
        val dir = File(backgroundDir)
        val files = dir.listFiles().some()
        cache = files.getOrElse(arrayOf()).filter { it.isFile }.map { BackgroundRes.path(it.name) }.toList()
    }
    fun get(index: Int): Option<Resources> = try {
        cache.get(index).some()
    } catch (e: Exception) {
        none()
    }

    fun getRandom(): Option<Resources> {
        val maxIndex = cache.size
        return get(Random.nextInt(0, maxIndex))
    }
}