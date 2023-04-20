package de.ping.lenneschule.domain.usecase.getschedule

import de.ping.lenneschule.common.Resource
import de.ping.lenneschule.data.remote.dto.toSchedule
import de.ping.lenneschule.domain.model.Schedule
import de.ping.lenneschule.domain.repository.ScheduleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class GetScheduleUseCase @Inject constructor(
    private val scheduleRepository: ScheduleRepository
) {

    operator fun invoke(
        token: String,
        cookie: String,
        date: LocalDate = LocalDate.now()
    ): Flow<Resource<Schedule>> = flow {
        emit(Resource.Loading())

        try {
            val schedule =
                scheduleRepository.getSchedule(
                    cookie = cookie,
                    token = token,
                    date = date.format(DateTimeFormatter.BASIC_ISO_DATE)
                )
            emit(Resource.Success(schedule.toSchedule()))
        } catch (exception: HttpException) {
            emit(Resource.Error(exception.localizedMessage.orEmpty()))
        } catch (exception: IOException) {
            emit(Resource.Error("Could not reach the server. Please check your internet connection"))
        }
    }
}