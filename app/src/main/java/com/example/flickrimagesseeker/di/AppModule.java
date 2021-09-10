package com.example.flickrimagesseeker.di;

import android.content.Context;

import com.example.flickrimagesseeker.App;
import com.example.flickrimagesseeker.api.FlickrApi;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.flickrimagesseeker.api.FlickrApi.API_BASE_URL;

@Module
@InstallIn(ApplicationComponent.class)
public class AppModule {


    @Provides
    public static CompositeDisposable providesCompositeDisposable(){
        return new CompositeDisposable();
    }

    @Provides
    @Singleton
    public static App providesApplication(Context context) {
        return (App) context;
    }

    @Provides
    @Singleton
    public static Retrofit provideRetrofit(
            // Potential dependencies of this type
    ) {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);

        return new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .client(new OkHttpClient.Builder().addInterceptor(loggingInterceptor).build())
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create()))
                .build();
    }

    @Provides
    @Singleton
    public static FlickrApi provideFlickrApi(Retrofit retrofit) {
        return provideRetrofit().create(FlickrApi.class);
    }
}
