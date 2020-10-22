package moe.liar.model

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
val CssRes = Static("/static/js/")
val IconRes = Static("/static/img/icon/")
val IconSvgRes = Static("/static/img/icon/svg/")