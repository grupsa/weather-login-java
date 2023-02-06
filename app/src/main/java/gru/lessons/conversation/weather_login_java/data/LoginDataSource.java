package gru.lessons.conversation.weather_login_java.data;

import gru.lessons.conversation.weather_login_java.data.model.LoggedInUser;

import java.io.IOException;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    public Result<LoggedInUser> login(String username, String password) {

        try {
//             TODO: handle loggedInUser authentication
            LoggedInUser fakeUser =
                    new LoggedInUser(
                            java.util.UUID.randomUUID().toString(),
                            "Evgeniy");
            return new Result.Success<>(fakeUser);
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
        // return new Result.Error(new IOException("Error logging in", new Throwable("invalid name")));
    }

    public void logout() {
        // TODO: revoke authentication
    }
}