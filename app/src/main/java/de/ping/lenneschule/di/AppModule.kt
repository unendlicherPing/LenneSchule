package de.ping.lenneschule.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import de.ping.lenneschule.common.Constants
import de.ping.lenneschule.data.remote.ScheduleApi
import de.ping.lenneschule.data.repository.ScheduleRepositoryImpl
import de.ping.lenneschule.domain.repository.ScheduleRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideScheduleApi(): ScheduleApi = Retrofit.Builder().baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ScheduleApi::class.java)

    @Provides
    @Singleton
    fun provideScheduleRepository(scheduleApi: ScheduleApi): ScheduleRepository =
        ScheduleRepositoryImpl(scheduleApi)
}