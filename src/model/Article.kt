package moe.liar.model

import moe.liar.utils.*

data class ArticleInfo(
    val articleId: Int,
    val title: String,
    val date: String,
    val preview: String,
    val tags: List<String>,
    val imageRes: Option<Resources> = none(),
)

data class Article(val articleId: Int, val title: String, val date: String, val content: String, val tags: List<String>, val imageRes: Option<Resources>)

fun Article.generateInfo(previewSize: Int = 30) = ArticleInfo(articleId, title, date, content.substring(0, previewSize), tags, imageRes)
