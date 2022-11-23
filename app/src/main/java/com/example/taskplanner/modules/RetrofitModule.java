package com.example.taskplanner.modules;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public class RetrofitModule {
    @Provides
    public static Retrofit provideRetrofit(){
        return new Retrofit.Builder()
                .baseUrl("https://ieti-tasks.herokuapp.com/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
