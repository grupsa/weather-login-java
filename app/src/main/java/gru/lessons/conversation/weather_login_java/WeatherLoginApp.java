package gru.lessons.conversation.weather_login_java;

import android.app.Application;

public class WeatherLoginApp extends Application {

    public static WeatherLoginAppComponent diComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        diComponent = DaggerWeatherLoginAppComponent.create();
    }
}
