package gru.lessons.conversation.weather_login_java.ui.login;

import android.util.Patterns;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import gru.lessons.conversation.weather_login_java.R;
import gru.lessons.conversation.weather_login_java.data.login.LoginRepository;
import gru.lessons.conversation.weather_login_java.data.login.Result;
import gru.lessons.conversation.weather_login_java.data.model.LoggedInUser;
import gru.lessons.conversation.weather_login_java.data.weather.WeatherRepo;
import gru.lessons.conversation.weather_login_java.data.weather.YandexWeatherResponse;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginViewModel extends ViewModel {

    private final MutableLiveData<LoginFormState> loginFormState = new MutableLiveData<>();
    private final MutableLiveData<LoginResult> loginResult = new MutableLiveData<>();
    private final MutableLiveData<YandexWeatherResponse> weatherResult = new MutableLiveData<>();
    private final MutableLiveData<String> liveError = new MutableLiveData<>();
    private final LoginRepository loginRepository;
    private final WeatherRepo weatherRepo;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject LoginViewModel(
            LoginRepository loginRepository,
            WeatherRepo weatherRepo) {
        this.loginRepository = loginRepository;
        this.weatherRepo = weatherRepo;
    }

    public LiveData<LoginFormState> getLoginFormState() {
        return loginFormState;
    }

    public LiveData<LoginResult> getLoginResult() { return loginResult; }

    public LiveData<String> getLiveError() { return liveError; }

    public LiveData<YandexWeatherResponse> getWeather() {
        Disposable disposable = weatherRepo.getWeather()
                .subscribeOn(Schedulers.io())
                .subscribe(weatherResult::postValue, this::onWeatherError);
        compositeDisposable.add(disposable);
        return weatherResult;
    }

    public void login(String username, String password) {
        // can be launched in a separate asynchronous job
        Thread myThread = new Thread(() -> {
            threadSleep(1000);

            Result<LoggedInUser> result = loginRepository.login(username, password);

            if (result instanceof Result.Success) {
                LoggedInUser data = ((Result.Success<LoggedInUser>) result).getData();
                loginResult.postValue(new LoginResult(new LoggedInUserView(data.getDisplayName())));
            } else {
                loginResult.postValue(new LoginResult(R.string.login_failed));
            }
        });
        myThread.start();
    }

    public void loginDataChanged(String username, String password) {
        if (!isUserNameValid(username)) {
            loginFormState.setValue(new LoginFormState(R.string.invalid_username, null));
        } else if (!isPasswordValid(password)) {
            loginFormState.setValue(new LoginFormState(null, R.string.invalid_password));
        } else {
            loginFormState.setValue(new LoginFormState(true));
        }
    }

    @Override
    protected void onCleared() {
        compositeDisposable.clear();
        super.onCleared();
    }

    private void onWeatherError(Throwable t) {
        liveError.postValue(t.getLocalizedMessage());
    }

    private void threadSleep(Integer timeMs) {
        try {
            Thread.sleep(timeMs);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // A placeholder username validation check
    private boolean isUserNameValid(String username) {
        if (username == null) {
            return false;
        }
        if (username.contains("@")) {
            return Patterns.EMAIL_ADDRESS.matcher(username).matches();
        } else {
            return !username.trim().isEmpty();
        }
    }

    // A placeholder password validation check
    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 5;
    }
}