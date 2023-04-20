package de.ping.lenneschule.domain.usecase.gettoken

import com.github.kittinunf.fuel.core.Headers
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import de.ping.lenneschule.common.Constants
import de.ping.lenneschule.common.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.jsoup.Jsoup

class GetTokenUseCase {

    operator fun invoke(
        cookie: String,
    ): Flow<Resource<String>> = flow {
        emit(Resource.Loading())

        val (_, tokenResponse, tokenResult) = Constants.scheduleUrl.httpGet()
            .header(Headers.COOKIE, cookie).response()

        when (tokenResult) {
            is Result.Failure -> {
                emit(Resource.Error(tokenResult.getException().localizedMessage.orEmpty()))
                return@flow
            }
            is Result.Success -> {}
        }

        val element =
            Jsoup.parse(tokenResponse.body().asString("text/html")).getElementById("token")

        if (element == null) {
            emit(Resource.Error("error reading token"))
            return@flow
        }

        val token = element.attr("name")

        if (token.isNullOrEmpty()) {
            emit(Resource.Error("error reading token"))
            return@flow
        }

        emit(Resource.Success(token))
    }
}