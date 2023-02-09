package gru.lessons.conversation.weather_login_java.data.login;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class LoginDiModule {
    @Provides
    @Singleton
    LoginRepository provideLoginRepo() {
        return LoginRepository.getInstance(new LoginDataSource());
    }
}
