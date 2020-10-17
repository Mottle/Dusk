package moe.liar.page

import kotlinx.html.*
import moe.liar.utils.None
import moe.liar.utils.Option
import moe.liar.utils.flatMap
import moe.liar.utils.some

interface Layout : (HTML, Page) -> Unit

class MainLayout(
    private val static: Option<String>,
    private val mainScript: Option<String>,
    private val mainStyle: Option<String>
) : Layout {
    override fun invoke(html: HTML, page: Page) = with(html) {
        head {
            unsafe {
                +"<meta charset=\"utf-8\" />"
                +"<meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">"
            }
            unsafe {
                +"<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/css/bootstrap.min.css\" integrity=\"sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk\" crossorigin=\"anonymous\">"
            }
            if(!static.isNone() && !mainStyle.isNone()) styleLink("${static.forceGet()}/${mainStyle.forceGet()}")
            static.flatMap {
                mainStyle.flatMap { _style ->
                    styleLink("$it/$_style").some()
                }
            }
            page.head(this)
        }
        body {
            static.flatMap {
                mainScript.flatMap { _script ->
                    script(src = "$it/$_script") {}.some()
                }
            }
            page.body(this)
            unsafe {
                +"<script src=\"https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js\" integrity=\"sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj\" crossorigin=\"anonymous\"></script>"
                +"<script src=\"https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js\" integrity=\"sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo\" crossorigin=\"anonymous\"></script>"
                +"<script src=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/js/bootstrap.min.js\" integrity=\"sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI\" crossorigin=\"anonymous\"></script>"
            }
        }
    }
}