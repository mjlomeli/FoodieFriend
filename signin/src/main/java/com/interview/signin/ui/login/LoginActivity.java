package com.interview.signin.ui.login;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuth.AuthStateListener;
import com.google.firebase.auth.FirebaseUser;
import com.interview.signin.R;
import android.app.Activity;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.graphics.Typeface;
import android.location.Address;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class LoginActivity extends AppCompatActivity {
    public static final int RC_SIGN_IN = 1;

    private LoginViewModel loginViewModel;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private Button createAccount;
    private Button googleButton;
    private ProgressBar loadingProgressBar;
    private TextView indicator;
    private CheckBox showPassword;
    private CheckBox keepMeSignedIn;

    private FirebaseAuth mFirebaseAuth;
    private AuthStateListener authStateListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mFirebaseAuth = FirebaseAuth.getInstance();

        authStateListener = new AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() != null) {
                    updateUI(firebaseAuth.getCurrentUser());
                }
                startActivityForResult(
                        AuthUI.getInstance()
                                .createSignInIntentBuilder()
                                .setAvailableProviders(Arrays.asList(
                                        new AuthUI.IdpConfig.GoogleBuilder().build()))
                                .build(),
                        RC_SIGN_IN);
            }

        };
        usernameEditText = (EditText) findViewById(R.id.username);
        passwordEditText = (EditText) findViewById(R.id.password);

        //TODO: remove the next line of code when ready to deploy
        usernameEditText.setText("anteater@uci.edu");
        passwordEditText.setText("zotzot");

        loginButton = (Button) findViewById(R.id.login);
        createAccount = (Button) findViewById(R.id.button_Create_Account);
        googleButton = (Button) findViewById(R.id.button_Google);
        loadingProgressBar = (ProgressBar) findViewById(R.id.loading);
        indicator = (TextView) findViewById(R.id.textView_Indicator);
        showPassword = (CheckBox) findViewById(R.id.checkBox_Show_Password);
        keepMeSignedIn = (CheckBox) findViewById(R.id.checkBox_Keep_Signed_In);

        googleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mFirebaseAuth.addAuthStateListener(new AuthStateListener() {
                    @Override
                    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                        if (firebaseAuth.getCurrentUser() != null){
                            updateUI(firebaseAuth.getCurrentUser());
                        }
                        else {
                            startActivityForResult(
                                    AuthUI.getInstance()
                                            .createSignInIntentBuilder()
                                            .setAvailableProviders(Arrays.asList(
                                                    new AuthUI.IdpConfig.GoogleBuilder().build(),
                                                    new AuthUI.IdpConfig.FacebookBuilder().build(),
                                                    new AuthUI.IdpConfig.TwitterBuilder().build(),
                                                    new AuthUI.IdpConfig.MicrosoftBuilder().build(),
                                                    new AuthUI.IdpConfig.YahooBuilder().build(),
                                                    new AuthUI.IdpConfig.AppleBuilder().build(),
                                                    new AuthUI.IdpConfig.EmailBuilder().build(),
                                                    new AuthUI.IdpConfig.PhoneBuilder().build(),
                                                    new AuthUI.IdpConfig.AnonymousBuilder().build()))
                                            .build(),
                                    RC_SIGN_IN);
                        }
                    }
                });
            }
        });

        loginViewModel = ViewModelProviders.of(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);

        loginViewModel.getLoginFormState().observe(this, new Observer<LoginFormState>() {
            @Override
            public void onChanged(@Nullable LoginFormState loginFormState) {
                if (loginFormState == null) {
                    return;
                }
                loginButton.setEnabled(loginFormState.isDataValid());
                if (loginFormState.getUsernameError() != null) {
                    usernameEditText.setError(getString(loginFormState.getUsernameError()));
                }
                if (loginFormState.getPasswordError() != null) {
                    passwordEditText.setError(getString(loginFormState.getPasswordError()));
                }
            }
        });

        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                loginViewModel.loginDataChanged(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        };
        usernameEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    loginViewModel.login(usernameEditText.getText().toString(),
                            passwordEditText.getText().toString());
                }
                return false;
            }
        });

        loginViewModel.loginDataChanged(usernameEditText.getText().toString(),
                passwordEditText.getText().toString());
    }



    private void updateUiWithUser(Task<AuthResult> authResultTask) {
        authResultTask.addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    FirebaseUser user = mFirebaseAuth.getCurrentUser();
                    updateUI(user);
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(LoginActivity.this, "Authentication failed.",
                            Toast.LENGTH_SHORT).show();
                    updateUI(null);
                }
            }
        });
    }

    private void showLoginFailed(final TextView indicator) {
        indicator.setText("username or password is incorrect");
    }

    public void onClick_SignIn(View view){
        loadingProgressBar.setVisibility(View.VISIBLE);
        mFirebaseAuth.signInWithEmailAndPassword(usernameEditText.getText().toString(),
                passwordEditText.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mFirebaseAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                            showLoginFailed(indicator);
                        }
                    }
                });
    }


    private void createAccount(String username, String password){
        mFirebaseAuth.createUserWithEmailAndPassword(usernameEditText.getText().toString(),
                passwordEditText.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    FirebaseUser user = mFirebaseAuth.getCurrentUser();
                    updateUI(user);
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(LoginActivity.this, "Authentication failed.",
                            Toast.LENGTH_SHORT).show();
                    updateUI(null);
                }
            }
        });
    }

    public void onClick_CreateNewAccount(View view){
        //TODO: Create an account form and Link a create account button
    }


    public void onClick_ShowPassword(View view){
        if (showPassword.isChecked())
            passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT);
        else {
            passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            passwordEditText.setTypeface(Typeface.DEFAULT);
        }
    }

    public void onClick_KeepMeSignedIn(View view){
        //TODO: set sign in options to stay signed in
    }

    public void updateUI(FirebaseUser user){
        if (user != null) {


            String welcome = getString(R.string.welcome) + user.getDisplayName();

            /////////////////////////////////////////
            // TODO : initiate the next intent here
            /////////////////////////////////////////

            Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mFirebaseAuth.getCurrentUser();
        updateUI(currentUser);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mFirebaseAuth.removeAuthStateListener(authStateListener);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mFirebaseAuth.addAuthStateListener(authStateListener);
    }



}
