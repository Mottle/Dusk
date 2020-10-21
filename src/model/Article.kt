package moe.liar.model

import moe.liar.utils.*

data class ArticleData(
    val articleId: Int,
    val title: String,
    val date: String,
    val preview: String,
    val tags: List<String>,
    val imageName: Option<String>,
)