package moe.liar.page

import kotlinx.html.*

class NavBar : Page {
    override fun head(htmlHead: HEAD) = Unit

    override fun body(htmlBody: BODY) {
        htmlBody.header {
            bodyHeader(this)
        }
    }

    private fun bodyHeader(header: HEADER) = with(header) {
        div("navbar navbar-expand-lg navbar-dark bg-dark") {
            a(href = "/", classes = "navbar-brand") {
                +"Outside"
            }
        }
    }

    override fun script(htmlBody: BODY) {
        TODO("Not yet implemented")
    }
}