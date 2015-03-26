package com.dev.kylesmith.myriadmobilechallenge.controller;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.dev.kylesmith.myriadmobilechallenge.R;
import com.dev.kylesmith.myriadmobilechallenge.model.RestClient;
import com.dev.kylesmith.myriadmobilechallenge.model.SubscriptionResponse;
import com.dev.kylesmith.myriadmobilechallenge.model.User;


import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedByteArray;


public class LoginActivity extends Activity {

    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */

    protected String responseMessage;
    private String subscriptionResponseMessage;
    private RestClient restClient = new RestClient();
    private FrameLayout mProgressBarHolder;
    private Button mEmailSignInButton;

    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mNameView;
    private View mProgressView;
    private View mLoginFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        RedirectIfSignedUp();


        // Set up the login form.
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        mProgressBarHolder = (FrameLayout) findViewById(R.id.progressBarHolder);
        mNameView = (EditText) findViewById(R.id.name);


        mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }






    private void RedirectIfSignedUp(){
        SharedPreferences settings = getSharedPreferences(getString(R.string.SHARED_PREF_NAME), 0);
        String userEmail = settings.getString(getString(R.string.USER_EMAIL_KEY), "");
        if(!userEmail.isEmpty()){ // user has signed up
            // Redirect to Kingdom List Activity
            Intent intent = new Intent(this, KingdomListActivity.class);
            startActivity(intent);
        }
    }





    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    public void attemptLogin() {

        // Reset errors.
        mEmailView.setError(null);
        mNameView.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mNameView.getText().toString();

        boolean cancel = false;
        View focusView = null;


        // Check for a valid name, if the user entered one.
        if (!isNameValid(password)) {
            mNameView.setError(getString(R.string.error_invalid_password));
            focusView = mNameView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);
            restClient.get().subscribe(new User(email), new SubscriptionCallback());
        }
    }








    private boolean isEmailValid(String email) {
        return email.contains("@");
    }







    private boolean isNameValid(String name) {
        return  !TextUtils.isEmpty(name);
    }








    /**
     * Shows the progress UI and hides the login form.
     */
    public void showProgress(final boolean show) {
        if(show){
            mEmailSignInButton.setEnabled(false);
            mProgressBarHolder.setAnimation(new Animations().getInAnimation());
            mProgressBarHolder.setVisibility(View.VISIBLE);
        }
        else{
            mEmailSignInButton.setEnabled(true);
            mProgressBarHolder.setAnimation(new Animations().getOutAnimation());
            mProgressBarHolder.setVisibility(View.INVISIBLE);
        }
    }





    private class SubscriptionCallback implements Callback<SubscriptionResponse>{
        @Override
        public void success(SubscriptionResponse subscriptionResponse, Response response2) {
            // Save user email
            SharedPreferences settings = getSharedPreferences(getString(R.string.SHARED_PREF_NAME), 0);
            SharedPreferences.Editor editor = settings.edit();
            editor.putString(getString(R.string.USER_EMAIL_KEY), mEmailView.getText().toString());
            editor.commit();

            showProgress(false);

            // Open Kingdom List Page
            Intent intent = new Intent(getApplicationContext(), KingdomListActivity.class);
            startActivity(intent);
        }

        @Override
        public void failure(RetrofitError error) {
            // Remove progress
            showProgress(false);

            // Display email error
            mEmailView.setError(getString(R.string.error_invalid_email));
            mEmailView.requestFocus();
        }
    }
}



