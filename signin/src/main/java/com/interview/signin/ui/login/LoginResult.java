package com.interview.signin.ui.login;

/**
 * @author Mauricio Lomeli
 * @version March, 2020
 *
 * This program generates a login system for Users to
 * authenticate. Special thanks to Google who has provided the code.
 */

import androidx.annotation.Nullable;

class LoginResult {
    @Nullable
    private LoggedInUserView success;
    @Nullable
    private Integer error;

    LoginResult(@Nullable Integer error) {
        this.error = error;
    }

    LoginResult(@Nullable LoggedInUserView success) {
        this.success = success;
    }

    @Nullable
    LoggedInUserView getSuccess() {
        return success;
    }

    @Nullable
    Integer getError() {
        return error;
    }
}
