package moe.liar.handler

import io.ktor.http.*
import io.ktor.locations.*
import moe.liar.model.InternetRandomImgAPI
import moe.liar.page.PBuilder
import moe.liar.page.Status
import moe.liar.utils.Option
import moe.liar.utils.getOrElse
import moe.liar.utils.option
import moe.liar.utils.some

@KtorExperimentalLocationsAPI
@Location("/status/{code}")
class StatusPageHandler(private val code: Int) : Handler<PBuilder> {
    override suspend fun handle(): PBuilder {
        val message = HttpStatusCode.allStatusCodes.find { it.value == code }.option() as Option<*>
        return {
            Status.Builder()
                .setStatus("${message.getOrElse("$code Unknown Error")}")
                .setBackGround(InternetRandomImgAPI.link(0))
                .build()
        }
    }
}