package moe.liar.model

interface Resources {
    abstract fun uri(): String
}

class Link (private val link: String): Resources {
    override fun uri(): String = link
}

class Static(rootPath: String) {
    private val root: String = if(rootPath.last() == '/') rootPath else "$rootPath/"

    class StaticPath(private val root: String, private val name: String) : Resources {
        override fun uri(): String = "$root$name"
    }
}

val ImgRes = Static("/static/img/")