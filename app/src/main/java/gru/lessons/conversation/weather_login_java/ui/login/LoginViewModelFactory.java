package gru.lessons.conversation.weather_login_java.ui.login;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.annotation.NonNull;

import gru.lessons.conversation.weather_login_java.WeatherLoginApp;

/**
 * ViewModel provider factory to instantiate LoginViewModel.
 * Required given LoginViewModel has a non-empty constructor
 */
public class LoginViewModelFactory implements ViewModelProvider.Factory {

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(LoginViewModel.class)) {
            //return (T) new LoginViewModel(LoginRepository.getInstance(new LoginDataSource()));
            return  (T) WeatherLoginApp.diComponent.getLoginViewModel();
        } else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}