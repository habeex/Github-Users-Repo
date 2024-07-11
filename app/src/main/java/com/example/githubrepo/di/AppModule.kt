package com.example.githubrepo.di

import android.app.Application
import com.example.githubrepo.data.manager.AppConnectivityManger
import com.example.githubrepo.data.manager.RetrofitManager
import com.example.githubrepo.data.manager.RetrofitManager.getRetrofit
import com.example.githubrepo.data.remote.RepositoriesApi
import com.example.githubrepo.data.remote.UserApi
import com.example.githubrepo.util.Constants
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

    @Provides
    @Singleton
    fun provideApiInstance(): UserApi =
        getRetrofit().create(UserApi::class.java)

    @Provides
    @Singleton
    fun provideReposApiInstance(): RepositoriesApi =
        getRetrofit().create(RepositoriesApi::class.java)

    @Provides
    @Singleton
    fun provideConnectivityManager(
        application: Application
    ) = AppConnectivityManger(application)

}