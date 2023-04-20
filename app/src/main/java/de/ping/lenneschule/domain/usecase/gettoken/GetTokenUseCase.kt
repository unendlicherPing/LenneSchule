/*
    Copyright (C) 2023  Moritz Pollack

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
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