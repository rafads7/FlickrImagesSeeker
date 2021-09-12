package com.example.flickrimagesseeker.di;

import android.app.SearchManager;
import android.content.Context;
import android.widget.SearchView;

import com.example.flickrimagesseeker.App;
import com.example.flickrimagesseeker.api.FlickrApi;
import com.example.flickrimagesseeker.ui.adapters.ImagesListAdapter;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ActivityContext;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.flickrimagesseeker.api.FlickrApi.API_BASE_URL;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {

    @Provides
    public static CompositeDisposable providesCompositeDisposable(){
        return new CompositeDisposable();
    }

    @Provides
    public static ImagesListAdapter providesImagesListAdapter(){
        return new ImagesListAdapter();
    }

    @Provides
    @Singleton
    public static Retrofit provideRetrofit() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);

        return new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .client(new OkHttpClient.Builder().addInterceptor(loggingInterceptor).build())
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create()))
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    public static FlickrApi provideFlickrApi(Retrofit retrofit) {
        return provideRetrofit().create(FlickrApi.class);
    }
}
