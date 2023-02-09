package gru.lessons.conversation.weather_login_java.data.weather;

import io.reactivex.Single;

public interface WeatherRepo {
    Single<YandexWeatherResponse> getWeather();
}
