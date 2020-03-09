package com.interview.signin.ui.login;

/**
 * @author Mauricio Lomeli
 * @version March, 2020
 *
 * This program generates a login system for Users to
 * authenticate. Special thanks to Google who has provided the code.
 */

import androidx.annotation.Nullable;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

class LoginResult {
    @Nullable
    private Task<AuthResult> success;
    @Nullable
    private Integer error;

    LoginResult(@Nullable Integer error) {
        this.error = error;
    }

    LoginResult(@Nullable Task<AuthResult> success) {
        this.success = success;
    }

    @Nullable
    Task<AuthResult> getSuccess() {
        return success;
    }

    @Nullable
    Integer getError() {
        return error;
    }
}
