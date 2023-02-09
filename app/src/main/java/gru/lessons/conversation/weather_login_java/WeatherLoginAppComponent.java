package gru.lessons.conversation.weather_login_java;

import javax.inject.Singleton;

import dagger.Component;
import gru.lessons.conversation.weather_login_java.data.login.LoginDiModule;
import gru.lessons.conversation.weather_login_java.data.weather.WeatherDiModule;
import gru.lessons.conversation.weather_login_java.ui.login.LoginViewModel;

@Singleton
@Component(modules = {LoginDiModule.class, WeatherDiModule.class})
public interface WeatherLoginAppComponent {
    LoginViewModel getLoginViewModel();
}
