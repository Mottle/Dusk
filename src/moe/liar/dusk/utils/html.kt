package moe.liar.dusk.utils

import kotlinx.html.HTML
import kotlinx.html.html
import kotlinx.html.stream.appendHTML

fun html(fn: (HTML).() -> Unit): String = buildString {
    appendLine("<!DOCTYPE html>")
    appendHTML().html{
        fn(this)
    }
    appendLine()
}