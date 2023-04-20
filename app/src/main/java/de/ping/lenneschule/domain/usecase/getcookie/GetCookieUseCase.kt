package de.ping.lenneschule.domain.usecase.getcookie

import com.github.kittinunf.fuel.core.Headers
import com.github.kittinunf.fuel.core.extensions.authentication
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import de.ping.lenneschule.common.Constants
import de.ping.lenneschule.common.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class GetCookieUseCase {

    operator fun invoke(username: String, password: String): Flow<Resource<String>> = flow {
        emit(Resource.Loading())

        val (_, cookieResponse, cookieResult) = Constants.scheduleUrl.httpGet().authentication()
            .basic(username, password).response()

        val cookie = when (cookieResult) {
            is Result.Failure -> {
                emit(Resource.Error(cookieResult.getException().localizedMessage.orEmpty()))
                return@flow
            }
            is Result.Success -> {
                val cookieHeader = cookieResponse.header(Headers.SET_COOKIE).toString()
                cookieHeader.substring(1, cookieHeader.length - 1)
            }
        }

        emit(Resource.Success(cookie))
    }
}