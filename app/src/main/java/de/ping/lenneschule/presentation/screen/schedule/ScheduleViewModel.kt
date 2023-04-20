package de.ping.lenneschule.presentation.screen.schedule

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import de.ping.lenneschule.common.Resource
import de.ping.lenneschule.domain.usecase.getcookie.GetCookieUseCase
import de.ping.lenneschule.domain.usecase.getschedule.GetScheduleUseCase
import de.ping.lenneschule.domain.usecase.gettoken.GetTokenUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class ScheduleViewModel @Inject constructor(
    private val getScheduleUseCase: GetScheduleUseCase,
) : ViewModel() {

    private val _scheduleUiState: MutableStateFlow<ScheduleUiState> =
        MutableStateFlow(ScheduleUiState())
    val scheduleUiState: StateFlow<ScheduleUiState> = _scheduleUiState.asStateFlow()

    private var cookie: String = ""
    private var token: String = ""

    init {
        updateCookie()
        updateToken()
        updateSchedule(_scheduleUiState.value.currentDate)
    }

    fun setDate(year: Int, month: Int, day: Int) {
        _scheduleUiState.update {
            it.copy(
                currentDate = LocalDate.of(year, month, day)
            )
        }

        if (!_scheduleUiState.value.schedules.containsKey(_scheduleUiState.value.currentDate)) updateSchedule(
            _scheduleUiState.value.currentDate
        )
    }

    fun nextDay() {
        _scheduleUiState.update {
            it.copy(currentDate = it.currentDate.plusDays(1))
        }

        if (!_scheduleUiState.value.schedules.containsKey(_scheduleUiState.value.currentDate)) updateSchedule(
            _scheduleUiState.value.currentDate
        )
    }

    fun previousDay() {
        _scheduleUiState.update {
            it.copy(currentDate = it.currentDate.minusDays(1))
        }

        if (!_scheduleUiState.value.schedules.containsKey(_scheduleUiState.value.currentDate)) updateSchedule(
            _scheduleUiState.value.currentDate
        )
    }

    fun updateSchedule(date: LocalDate = LocalDate.now()) =
        getScheduleUseCase(token, cookie, date).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    val schedule = result.data!!

                    _scheduleUiState.update {
                        it.apply {
                            this.schedules[date] = schedule
                        }
                        it.copy(
                            isLoading = false
                        )
                    }
                }

                is Resource.Error -> {
                    _scheduleUiState.update {
                        it.copy(
                            error = result.message ?: "An unexpected error occured",
                            isLoading = false
                        )
                    }
                }

                is Resource.Loading -> {
                    _scheduleUiState.update {
                        it.copy(
                            isLoading = true
                        )
                    }
                }
            }
        }.launchIn(viewModelScope)

    private fun updateCookie() = GetCookieUseCase()("vertretung", "lenne2017").onEach { result ->
        when (result) {
            is Resource.Success -> {
                cookie = result.data.orEmpty()
                Log.d("view model", cookie)

                _scheduleUiState.update {
                    it.copy(
                        isLoading = false
                    )
                }
            }

            is Resource.Error -> {
                _scheduleUiState.update {
                    it.copy(
                        error = result.message ?: "An unexpected error occured", isLoading = false
                    )
                }
            }

            is Resource.Loading -> {
                _scheduleUiState.update {
                    it.copy(
                        isLoading = true
                    )
                }
            }
        }
    }.launchIn(viewModelScope)

    private fun updateToken() =
        GetTokenUseCase()(cookie).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    token = result.data.toString()

                    _scheduleUiState.update {
                        it.copy(
                            isLoading = false
                        )
                    }
                }

                is Resource.Error -> {
                    _scheduleUiState.update {
                        it.copy(
                            error = result.message ?: "An unexpected error occured",
                            isLoading = false
                        )
                    }
                }

                is Resource.Loading -> {
                    _scheduleUiState.update {
                        it.copy(
                            isLoading = true
                        )
                    }
                }
            }
        }.launchIn(viewModelScope)

}