package moe.liar.model

import kotlinx.coroutines.*
import moe.liar.utils.*
import java.io.File
import java.lang.Integer.min
import java.text.SimpleDateFormat
import java.util.*

data class ArticlePreview(
    val articleId: Int,
    val title: String,
    val date: Date,
    val rowPreview: String,
    val tags: List<String>,
    val imageRes: Option<Resources> = none(),
) {
    val preview
        get() = MarkdownFactory.get(rowPreview).toHtml()

    fun formatDate(): String = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date)
}

data class Article(
    val articleId: Int,
    val title: String,
    val date: Date,
    val rowContent: String,
    val tags: List<String>,
    val imageRes: Option<Resources>
) {
    val content
        get() = MarkdownFactory.get(rowContent).toHtml()

    fun formatDate(): String = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date)
}

fun Article.preview(previewSize: Int = 100): ArticlePreview {
    val noTitleContent = rowContent.formatRowContent()
    return ArticlePreview(articleId, title, date, noTitleContent.splitNoTitleContent(previewSize), tags, imageRes)
}

private fun String.formatRowContent() = this.split("\n").filter { !it.trim().startsWith("#") }.joinToString("\n")

private fun String.splitNoTitleContent(previewSize: Int) = this.substring(0, min(previewSize, this.length))


object ArticleDAO {
    private var articles: List<Article> = listOf()
    suspend fun refresh() = withContext(Dispatchers.IO) {
        val dir = File("./resources/markdown/")
        val files = dir.listFiles().some()
        articles = files.getOrElse(arrayOf<File?>()).filter<File> { it.isFile }
            .mapIndexed { id, file -> markdownToArticle(file, id) }.filter { !it.isNone() }
            .map { it.forceGet()!! }.sortByDate().reversed()
    }

    fun get(id: Int): Option<Article> = articles.find { it.articleId == id }.let {
        it ?. some() ?: none()
    }

    suspend fun getAll(): List<Article> {
        refresh()
        return articles
    }
}

private fun List<Article>.sortByDate() = this.sortedBy { it.date }

private fun markdownToArticle(markdown: File, id: Int): Option<Article> {
    val lines = markdown.readLines()//.map { it.trim() }
    val splits = lines.splitToMetaAndContentLine()
    val metaLines = splits.first
    val meta = metaLines.parseMeta()
    val content = splits.second.joinToString("\n").some()
    return meta.flatMap { info ->
        content.flatMap {
            Article(
                articleId = id,
                title = info.first,
                date = info.third,
                rowContent = it,
                tags = info.second,
                imageRes = RandomImgAPI.link().some()
            ).some()
        }
    }
}

private fun List<String>.splitToMetaAndContentLine(): Pair<List<String>, List<String>> {
    val indexes = this.mapIndexed { index, s ->
        Pair(index, s.isMetaFlag())
    }.filter { it.second }
    val l = indexes.first().first
    val r = indexes[1].first
    val metaLines = this.subList(l + 1, r)
    val contentLines = this.subList(r + 1, this.size)
    return Pair(metaLines, contentLines)
}

private fun List<String>.parseMeta(): Option<Triple<String, List<String>, Date>> {
    val title = this.findPrefix("title:")
    val tags = this.findPrefix("tags:").map { it.split(" ") }
    val date = this.findPrefix("date:")
    return title.flatMap { _title ->
        tags.flatMap { _tags ->
            date.flatMap { _date ->
                Triple(_title, _tags, SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(_date)).some()
            }
        }
    }
}

private fun List<String>.findPrefix(prefix: String): Option<String> =
    this.find { it.startsWith(prefix) }?.removePrefix(prefix)?.trim().option() as Option<String>


private fun String.isMetaFlag() = this == "---"