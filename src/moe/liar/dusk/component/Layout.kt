package moe.liar.dusk.component

import kotlinx.html.*
import moe.liar.dusk.utils.Option
import moe.liar.dusk.utils.map

interface Layout : (HTML, Component) -> Unit

class MainLayout(
    val static: Option<String>,
    private val scripts: List<String>,
    private val styles: List<String>
) : Layout {


    override fun invoke(html: HTML, component: Component) = with(html) {
        head {
            unsafe {
                +"<meta charset=\"utf-8\" />"
                +"<meta content=\"width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0,\" name=\"viewport\" />"
                +"<link href=\"https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/4.5.3/css/bootstrap.min.css\" rel=\"stylesheet\">"
            }
            title("薄暮")
            static.map {
                styleLink("$it/css/fonts.css")
                styles.map { _style ->
                    styleLink("$it/css/$_style")
                }
            }
            component.head(this)
        }
        body {
            component.body(this)
            static.map {
                scripts.map { _script ->
                    script(src = "$it/js/$_script") {}
                }
            }
            unsafe {
                +"<script src=\"https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.min.js\" crossorigin=\"anonymous\"></script>"
                +"<script src=\"https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js\" integrity=\"sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo\" crossorigin=\"anonymous\"></script>"
                +"<script src=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/js/bootstrap.min.js\" integrity=\"sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI\" crossorigin=\"anonymous\"></script>"
                +"<script src=\"https://cdn.bootcdn.net/ajax/libs/animejs/3.2.1/anime.min.js\"></script>"
                +"""
                    <script>
                    MathJax = {
                      tex: {
                        inlineMath: [['${'$'}', '${'$'}'], ['\\(', '\\)']],
                        displayMath: [ ['${'$'}${'$'}','${'$'}${'$'}'], ["\\[","\\]"] ],
                        processEscapes: true
                      },
                      svg: {
                        fontCache: 'global'
                      }
                    };
                    </script>
                """.trimIndent()
                +"<script src=\"https://polyfill.io/v3/polyfill.min.js?features=es6\"></script>"
                +"<script id=\"MathJax-script\" async src=\"https://cdn.jsdelivr.net/npm/mathjax@3/es5/tex-mml-chtml.js\"></script>"
            }
            component.script(this)
        }
    }
}
