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