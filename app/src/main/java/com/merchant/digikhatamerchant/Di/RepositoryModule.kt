package com.digikhata.merchant.Di

import android.content.SharedPreferences
import com.digikhata.merchant.Api.ApiEndpoints
import com.merchant.digikhatamerchant.Repo.ChangePasswordRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideChangePasswordDataRepository(apiService: ApiEndpoints, sharedPrefs: SharedPreferences) =
        ChangePasswordRepository(apiService, sharedPrefs)
    /*@Binds
    @Singleton
    abstract fun bindWeatherRepository(): WeatherRepository*/

   /* @Singleton
    @Provides
    fun provideWeatherDataRepository(apiService: ApiEndpoints, sharedPrefs: SharedPreferences) =
        WeatherRepository(apiService, sharedPrefs)

    @Singleton
    @Provides
    fun provideConfigDataRepository(apiService: ApiEndpoints, sharedPrefs: SharedPreferences) =
        GetConfigRepository(apiService, sharedPrefs)

    @Singleton
    @Provides
    fun provideLoginDataRepository(apiService: ApiEndpoints, sharedPrefs: SharedPreferences) =
        LoginRepository(apiService, sharedPrefs)

    @Singleton
    @Provides
    fun provideSignUpDataRepository(apiService: ApiEndpoints, sharedPrefs: SharedPreferences) =
        SignupRepository(apiService, sharedPrefs)

    @Singleton
    @Provides
    fun provideForgetPasswordDataRepository(apiService: ApiEndpoints, sharedPrefs: SharedPreferences) =
        ForgotPasswordRepository(apiService, sharedPrefs)



    @Singleton
    @Provides
    fun provideVerifyPhoneNumberRepository(apiService: ApiEndpoints, sharedPrefs: SharedPreferences) =
        VerifyPhoneNumberRepository(apiService, sharedPrefs)

    @Singleton
    @Provides
    fun provideForgotVerifyOtpRepository(apiService: ApiEndpoints, sharedPrefs: SharedPreferences) =
        ForgotVerifyOtpRepository(apiService, sharedPrefs)

    @Singleton
    @Provides
    fun provideUpdateRegisteredPhoneNumberRepository(apiService: ApiEndpoints, sharedPrefs: SharedPreferences) =
        UpdateRegisteredPhoneNumberRepository(apiService, sharedPrefs)

    @Singleton
    @Provides
    fun provideVerifyEmailIdRepository(apiService: ApiEndpoints, sharedPrefs: SharedPreferences) =
        VerifyEmailIdRepository(apiService, sharedPrefs)

    @Singleton
    @Provides
    fun provideUpdateRegisteredEmailIdRepository(apiService: ApiEndpoints, sharedPrefs: SharedPreferences) =
        UpdateRegisteredEmailIdRepository(apiService, sharedPrefs)

    @Singleton
    @Provides
    fun provideGetCardsListRepository(apiService: ApiEndpoints, sharedPrefs: SharedPreferences) =
        GetCardsListRepository(apiService, sharedPrefs)

    @Singleton
    @Provides
    fun provideDeleteCardRepository(apiService: ApiEndpoints, sharedPrefs: SharedPreferences) =
        DeleteCardRepository(apiService, sharedPrefs)

    @Singleton
    @Provides
    fun provideSetCardDefaultStatusRepository(apiService: ApiEndpoints, sharedPrefs: SharedPreferences) =
        SetCardDefaultStatusRepository(apiService, sharedPrefs)

    @Singleton
    @Provides
    fun provideAddCardRepository(apiService: ApiEndpoints, sharedPrefs: SharedPreferences) =
        AddCardRepository(apiService, sharedPrefs)

    @Singleton
    @Provides
    fun provideGetUserProfileDetailsRepository(apiService: ApiEndpoints, sharedPrefs: SharedPreferences) =
        GetProfileDetailsRepository(apiService, sharedPrefs)

    @Singleton
    @Provides
    fun provideUpdateUserProfileDetailsRepository(apiService: ApiEndpoints, sharedPrefs: SharedPreferences) =
        UpdateProfileDetailsRepository(apiService, sharedPrefs)

    @Singleton
    @Provides
    fun provideSocialLoginUserRepository(apiService: ApiEndpoints, sharedPrefs: SharedPreferences) =
        SocialLoginRepository(apiService, sharedPrefs)*/
}