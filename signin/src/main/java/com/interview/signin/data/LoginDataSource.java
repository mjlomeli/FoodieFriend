package com.interview.signin.data;

import com.interview.signin.data.model.LoggedInUser;

import java.io.IOException;

/**
 * ONLY CHANGE logout() & login(username, password)
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    public Result<LoggedInUser> login(String username, String password) {

        try {
            // TODO: handle loggedInUser authentication by checking their username matches password
            if (username.equals("anteater") && password.equals("zotzot")) {
                LoggedInUser fakeUser =
                        new LoggedInUser(
                                java.util.UUID.randomUUID().toString(),
                                "anteater");
                return new Result.Success<>(fakeUser);
            }
            else {
                return new Result.Error(new IOException("Your username or password is incorrect"));
            }

        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
        // TODO: Remove all cached data here
    }
}

