package gru.lessons.conversation.weather_login_java.data.weather;

import gru.lessons.conversation.weather_login_java.data.model.ActualWeatherData;

public final class YandexWeatherResponse {
    private Integer now;
    private ActualWeatherData fact;


    public Integer getNow() {
        return now;
    }

    public void setNow(Integer now) {
        this.now = now;
    }

    public ActualWeatherData getFact() {
        return fact;
    }

    public void setFact(ActualWeatherData fact) {
        this.fact = fact;
    }
}
