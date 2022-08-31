package ru.profsoft.profnotes.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineExceptionHandler
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import ru.profsoft.profnotes.BuildConfig
import ru.profsoft.profnotes.data.model.RegisterResponse
import ru.profsoft.profnotes.data.network.api.NotesApi
import ru.profsoft.profnotes.data.network.api.RegisterApi
import ru.profsoft.profnotes.data.network.interseptor.AuthentificationInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val AUTH_OKHTTP_CLIENT = "authOkHttpClient"
    private const val WITHOUT_AUTH_OKHTTP_CLIENT = "withoutAuthOkHttpClient"

    @Provides
    fun provideLogger() = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Named(AUTH_OKHTTP_CLIENT)
    @Provides
    @Singleton
    fun provideAuthOkHttpClient(
        logger: HttpLoggingInterceptor
    ) = OkHttpClient.Builder()
        .readTimeout(30, TimeUnit.SECONDS)
        .connectTimeout(30, TimeUnit.SECONDS)
        .addInterceptor(AuthentificationInterceptor())
        .addInterceptor(logger)
        .build()

    @Named(WITHOUT_AUTH_OKHTTP_CLIENT)
    @Provides
    @Singleton
    fun provideWithoutAuthOkHttpClient(
        logger: HttpLoggingInterceptor
    ) = OkHttpClient.Builder()
        .readTimeout(30, TimeUnit.SECONDS)
        .connectTimeout(30, TimeUnit.SECONDS)
        .addInterceptor(logger)
        .build()

    @Provides
    fun provideMoshi() = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()

    @Named(AUTH_OKHTTP_CLIENT)
    @Provides
    @Singleton
    fun provideAuthRetrofit(
        @Named(AUTH_OKHTTP_CLIENT) okHttpClient: OkHttpClient,
        moshi: Moshi
    ) = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi).asLenient())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .client(okHttpClient)
        .build()

    @Named(WITHOUT_AUTH_OKHTTP_CLIENT)
    @Provides
    @Singleton
    fun provideWithoutAuthRetrofit(
        @Named(WITHOUT_AUTH_OKHTTP_CLIENT) okHttpClient: OkHttpClient,
        moshi: Moshi
    ) = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi).asLenient())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .client(okHttpClient)
        .build()

    @Provides
    fun provideNotesApi(
        @Named(AUTH_OKHTTP_CLIENT) retrofit: Retrofit
    ) = retrofit.create(NotesApi::class.java)

    @Provides
    fun provideRegisterApi(
        @Named(WITHOUT_AUTH_OKHTTP_CLIENT) retrofit: Retrofit
    ) = retrofit.create(RegisterApi::class.java)
}