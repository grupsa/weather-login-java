package gru.lessons.conversation.weather_login_java.data.weather;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import gru.lessons.conversation.weather_login_java.AppConfig;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class WeatherDiModule {

    @Provides
    @Singleton
    Gson provideGson() {
        GsonBuilder builder = new GsonBuilder();
        return builder.setLenient().create();
    }


    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder().addInterceptor(interceptor).build();
    }


    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
            .baseUrl(AppConfig.YANDEX_WEATHER_API_URL) // https://backend.example.com
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build();
    }

    @Provides
    @Singleton
    YandexWeatherProtocol provideWeatherRepositoryProtocol(Retrofit retrofit) {
        return retrofit.create(YandexWeatherProtocol.class);
    }

    @Provides
    @Singleton
    WeatherRepo provideWeatherRepository(YandexWeatherProtocol protocol) {
        return new YandexWeatherRepository(protocol);
    }

}
