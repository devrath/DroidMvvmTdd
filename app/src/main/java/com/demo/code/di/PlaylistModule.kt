package com.demo.code.di

import com.demo.code.fragments.playlist.PlaylistAPI
import com.demo.code.utils.Constants.APP_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(FragmentComponent::class)
class PlaylistModule {

    @Provides
    fun playlistApi(retrofit : Retrofit) : PlaylistAPI {
        return retrofit.create(PlaylistAPI::class.java)
    }

    @Provides
    fun retrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(APP_URL) // Sometimes it fails and we need to change this
            .client(OkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}