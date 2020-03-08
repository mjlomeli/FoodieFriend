package com.interview.signin.ui.login;
/**
 * @author Mauricio Lomeli
 * @version March, 2020
 *
 * This program generates a login system for Users to
 * authenticate. Special thanks to Google who has provided the code.
 */

class LoggedInUserView {
    private String displayName;
    //... other data fields that may be accessible to the UI

    LoggedInUserView(String displayName) {
        this.displayName = displayName;
    }

    String getDisplayName() {
        return displayName;
    }
}
