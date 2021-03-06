package com.levi9.daggerexample.dagger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.levi9.daggerexample.communication.CommunicationManager;
import com.levi9.daggerexample.communication.WeatherMapApi;

import android.content.Context;
import android.location.LocationManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

/**
 * Created by Major on 5/24/2015.
 */
@Module
public class ExampleModule {

    private Context mContext;

    public ExampleModule(Context context) {
        this.mContext = context;
    }

    @Singleton
    @Provides
    public CommunicationManager provideCommunication() {
        return new CommunicationManager(mContext);
    }

    @Provides
    @Singleton
    public RestAdapter provideRestAdapter() {
        return new RestAdapter.Builder() //
                .setClient(new OkClient()) //
                .setEndpoint(WeatherMapApi.API_URL) //
                .build();
    }

    @Provides
    @Singleton
    public WeatherMapApi provideWeatherApi(RestAdapter restAdapter) {
        return restAdapter.create(WeatherMapApi.class);
    }

    @Provides
    @Singleton
    public LocationManager provideLocationManager() {
        return (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
    }

}
