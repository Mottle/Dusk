package moe.liar.page

import kotlinx.html.*
import moe.liar.utils.Option
import moe.liar.utils.map

interface Layout : (HTML, Page) -> Unit

class MainLayout(
    val static: Option<String>,
    private val scripts: List<String>,
    private val styles: List<String>
) : Layout {


    override fun invoke(html: HTML, page: Page) = with(html) {
        head {
            unsafe {
                +"<meta charset=\"utf-8\" />"
//                +"<meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">"
                +"<meta content=\"width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0;\" name=\"viewport\" />"
            }
//            unsafe {
//                +"<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/css/bootstrap.min.css\" integrity=\"sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk\" crossorigin=\"anonymous\">"
//            }
            title("薄暮")
            static.map {
                styleLink("$it/css/bootstrap.min.css")
                styles.map { _style ->
                    styleLink("$it/css/$_style")
                }
            }
            page.head(this)
        }
        body {
            static.map {
                scripts.map { _script ->
                    script(src = "$it/js/$_script") {}
                }
                script(src = "$it/js/anime.min.js") {}
            }
            page.body(this)
            unsafe {
//                +"<script src=\"https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js\" integrity=\"sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj\" crossorigin=\"anonymous\"></script>"
                +"<script src=\"https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.min.js\" crossorigin=\"anonymous\"></script>"
                +"<script src=\"https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js\" integrity=\"sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo\" crossorigin=\"anonymous\"></script>"
                +"<script src=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/js/bootstrap.min.js\" integrity=\"sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI\" crossorigin=\"anonymous\"></script>"
            }
            page.script(this)
        }
    }
}

fun <P : Page> MainLayout.build(html: HTML, fn: (Option<String>) -> P) {
    val page = fn(static)
    this(html, page)
}