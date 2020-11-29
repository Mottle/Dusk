package moe.liar.dusk.handler

import io.ktor.http.*
import io.ktor.locations.*
import moe.liar.dusk.component.Component
import moe.liar.dusk.component.Status
import moe.liar.dusk.model.InternetRandomImgAPI
import moe.liar.dusk.utils.Option
import moe.liar.dusk.utils.getOrElse
import moe.liar.dusk.utils.option

@KtorExperimentalLocationsAPI
@Location("/status/{code}")
class StatusPageHandler(private val code: Int) : Handler<Component> {
    override suspend fun handle(): Component {
        val message = HttpStatusCode.allStatusCodes.find { it.value == code }.option() as Option<*>
        return Status.Builder()
            .setStatus("${message.getOrElse("$code Unknown Error")}")
            .setBackGround(InternetRandomImgAPI.link(0))
            .build()
    }
}