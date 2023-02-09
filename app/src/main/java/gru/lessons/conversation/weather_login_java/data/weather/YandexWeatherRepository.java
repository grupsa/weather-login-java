package gru.lessons.conversation.weather_login_java.data.weather;

import javax.inject.Inject;

import gru.lessons.conversation.weather_login_java.AppConfig;
import io.reactivex.Single;

class YandexWeatherRepository implements WeatherRepo {

    private YandexWeatherProtocol protocol;

    @Inject
    public YandexWeatherRepository(YandexWeatherProtocol protocol) {
        this.protocol = protocol;
    }

    @Override
    public Single<YandexWeatherResponse> getWeather() {
        return protocol.requestWeatherForCoords(AppConfig.TARGET_LATITUD, AppConfig.TARGET_LONGITUDE);
    }
}
