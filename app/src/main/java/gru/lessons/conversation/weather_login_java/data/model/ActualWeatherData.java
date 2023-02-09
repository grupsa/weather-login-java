package gru.lessons.conversation.weather_login_java.data.model;

public final class ActualWeatherData {
    private Integer temp;
    private WeatherCondition condition;

    public Integer getTemp() {
        return temp;
    }

    public void setTemp(Integer temp) {
        this.temp = temp;
    }

    public WeatherCondition getCondition() {
        return condition;
    }

    public void setCondition(WeatherCondition condition) {
        this.condition = condition;
    }
}

enum WeatherCondition {
    CLEAR("Ясно"),
    CLOUDY("Облачно");

    WeatherCondition(String description) { }
}