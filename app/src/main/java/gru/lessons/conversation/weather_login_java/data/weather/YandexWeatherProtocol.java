package gru.lessons.conversation.weather_login_java.data.weather;

import gru.lessons.conversation.weather_login_java.AppConfig;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

interface YandexWeatherProtocol {
    @Headers("X-Yandex-API-Key: " + AppConfig.YANDEX_WEATHER_API_KEY)
    @GET("/v1/informers")
    Single<YandexWeatherResponse> requestWeatherForCoords(
            @Query("lat") String latitude,
            @Query("lon") String longitude
    );
}
