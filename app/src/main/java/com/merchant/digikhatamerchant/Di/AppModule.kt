package com.digikhata.merchant.Di


import com.digikhata.merchant.Api.ApiEndpoints
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    /* @Provides
     @Singleton
     fun provideBaseUrl() = "https://api.open-meteo.com/"*/

    /*@Provides
    @Singleton
    fun provideConnectionTimeout() = NETWORK_TIMEOUT*/

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().setLenient().create()

    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Singleton
    @Provides
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor) =
        OkHttpClient
            .Builder()
            .connectTimeout(20, TimeUnit.SECONDS) // Set your desired connection timeout
            .readTimeout(20, TimeUnit.SECONDS)    // Set your desired read timeout
            .writeTimeout(20, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .build()


    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, client: OkHttpClient): Retrofit {
        /* var base_url: String = ""
         base_url = if (BuildConfig.DEBUG){
             "https://api.open-meteo.com/"
         } else{
             "https://api.open-meteo.com/"
         }*/
        // "https://mb2bstage.paypointindia.co.in/api/" Stg
        // "https://uat-mb2b.paypointindia.co.in/api/" Registration flow

     /*   var base_url: String = if (BuildConfig.FLAVOR.equals("dev", true)) {
            "https://mb2bstage.paypointindia.co.in/api/"
        } else if (BuildConfig.FLAVOR.equals("qa", true)) {
            "https://mb2b-preprod.paypointindia.co.in/api/"
        } else if (BuildConfig.FLAVOR.equals("prod", true)) {
            "https://mb2bstage.paypointindia.co.in/api/"
        } else if (BuildConfig.FLAVOR.equals("stg", true)) {
            "https://mb2bstage.paypointindia.co.in/api/"
        } else {
            "https://mb2b-preprod.paypointindia.co.in/api/"
        }*/
        val base_url=    "https://mb2b-preprod.paypointindia.co.in/api/"
        return Retrofit.Builder()
            .baseUrl(base_url/*BASE_URL*/)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiEndpoints =
        retrofit.create(ApiEndpoints::class.java)
}

