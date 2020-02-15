package com.example.smarthomeapp.login;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smarthomeapp.BaseActivity;
import com.example.smarthomeapp.MainActivity;
import com.example.smarthomeapp.R;
import com.example.smarthomeapp.app.SmartHomeApplication;
import com.example.smarthomeapp.util.Constants;
import com.example.smarthomeapp.util.HouseConfigService;
import com.example.smarthomeapp.util.RemoteUtils;
import com.example.smarthomeapp.util.SharedPreferencesUtils;
import com.example.smarthomeapp.util.UserAuthService;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import com.example.smarthomeapp.model.HomeConfigEntity;
import com.example.smarthomeapp.model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseActivity implements LoaderCallbacks<Cursor>, LoginContract.View {

    /**
     * Id to identity READ_CONTACTS permission request.
     */
    private static final int REQUEST_READ_CONTACTS = 0;

    private HouseConfigService houseConfigService;
    private UserAuthService userAuthService;
    private LoginContract.Presenter mLoginPresenter;


    /**
     * The M email view.
     */
    @BindView(R.id.email)
    AutoCompleteTextView mEmailView;

    /**
     * The M password view.
     */
    @BindView(R.id.password)
    EditText mPasswordView;

    /**
     * The M progress view.
     */
    @BindView(R.id.login_progress)
    View mProgressView;

    /**
     * The M login form view.
     */
    @BindView(R.id.login_form)
    View mLoginFormView;

    /**
     * The M email sign in button.
     */
    @BindView(R.id.email_sign_in_button)
    Button mEmailSignInButton;


    /**
     * The M house config loader.
     */
    @BindView(R.id.load_xml_loader)
    ProgressBar mHouseConfigLoader;

    /**
     * The M house config loader error.
     */
    @BindView(R.id.load_xml_loader_error)
    ImageView mHouseConfigLoaderError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        getSupportActionBar().hide();


        populateAutoComplete();


        mLoginPresenter = new LoginPresenter(this);

        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });


        mEmailSignInButton.setClickable(true);


        Retrofit retrofit = RemoteUtils.getRetrofitObj();
        houseConfigService = retrofit.create(HouseConfigService.class);
        userAuthService = retrofit.create(UserAuthService.class);

        loadHouseConfig();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_login;
    }

    private void populateAutoComplete() {
        if (!mayRequestContacts()) {
            return;
        }

        getLoaderManager().initLoader(0, null, this);
    }

    private boolean mayRequestContacts() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (checkSelfPermission(READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        if (shouldShowRequestPermissionRationale(READ_CONTACTS)) {
            Snackbar.make(mEmailView, R.string.permission_rationale, Snackbar.LENGTH_INDEFINITE)
                    .setAction(android.R.string.ok, new View.OnClickListener() {
                        @Override
                        @TargetApi(Build.VERSION_CODES.M)
                        public void onClick(View v) {
                            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
                        }
                    });
        } else {
            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
        }
        return false;
    }

    /**
     * Callback received when a permissions request has been completed.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == REQUEST_READ_CONTACTS) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                populateAutoComplete();
            }
        }
    }


    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {
        mEmailView.setError(null);
        mPasswordView.setError(null);


        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;


        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }


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
            focusView.requestFocus();
        } else {
            showProgress(true);

            userAuthService.login(email, password).enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    User loggedUser = response.body();
                    showProgress(false);

                    if (loggedUser != null) {
                        SmartHomeApplication.getInstance().setUserEntity(loggedUser);

                        Intent mainIntent = new Intent(LoginActivity.this, MainActivity.class);

                        LoginActivity.this.startActivity(mainIntent);
                        finish();
                    } else {
                        mPasswordView.setError(getString(R.string.error_incorrect_password));
                        mPasswordView.requestFocus();
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    //TODO check errors
                }
            });
        }
    }

    private void loadHouseConfig() {
        if (SmartHomeApplication.getInstance().getHomeConfiguration() != null) {
            mEmailSignInButton.setClickable(true);
            Toast.makeText(this, "House is already loaded", Toast.LENGTH_LONG).show();
        } else {
            mHouseConfigLoader.setVisibility(View.VISIBLE);

            houseConfigService.getHomeConfig().enqueue(new Callback<HomeConfigEntity>() {
                @Override
                public void onResponse(Call<HomeConfigEntity> call, Response<HomeConfigEntity> response) {
                    HomeConfigEntity homeConfigEntity = response.body();
                    mLoginPresenter.loadHouseConfiguration(homeConfigEntity);
                }

                @Override
                public void onFailure(Call<HomeConfigEntity> call, Throwable t) {
                    String errorMessage = t.getMessage();
//                    Toast.makeText(this, "Could not load House config from remote server", Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
//        return email.contains("@");
        return true;
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return true;
    }


    private void saveLoginData(String userId, boolean rememberMe) {
        SharedPreferencesUtils.setStringPreference(getBaseContext(), Constants.Login.USER_ID, userId);
        SharedPreferencesUtils.setBooleanPreference(getBaseContext(), Constants.Login.REMEMBER_ME, rememberMe);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(this,

                Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI,
                        ContactsContract.Contacts.Data.CONTENT_DIRECTORY), ProfileQuery.PROJECTION,


                ContactsContract.Contacts.Data.MIMETYPE +
                        " = ?", new String[]{ContactsContract.CommonDataKinds.Email
                .CONTENT_ITEM_TYPE},


                ContactsContract.Contacts.Data.IS_PRIMARY + " DESC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        List<String> emails = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            emails.add(cursor.getString(ProfileQuery.ADDRESS));
            cursor.moveToNext();
        }

        addEmailsToAutoComplete(emails);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {

    }

    private void addEmailsToAutoComplete(List<String> emailAddressCollection) {

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(LoginActivity.this,
                        android.R.layout.simple_dropdown_item_1line, emailAddressCollection);

        mEmailView.setAdapter(adapter);
    }

    @Override
    public void setPresenter(@NonNull LoginContract.Presenter presenter) {
        mLoginPresenter = presenter;
    }

    @Override
    public void setLoadingIndicator(boolean active) {

    }

    @Override
    public void showHouseConfigResult(HomeConfigEntity homeConfigEntity) {
        showProgress(false);
        mHouseConfigLoader.setVisibility(View.GONE);

        if (homeConfigEntity != null) {
            SmartHomeApplication.getInstance().setHomeConfiguration(homeConfigEntity);

            mEmailSignInButton.setClickable(true);
            Toast.makeText(LoginActivity.this, "House Load SUCCESSFUL", Toast.LENGTH_LONG).show();
        } else {
            mHouseConfigLoaderError.setVisibility(View.VISIBLE);
            Toast.makeText(LoginActivity.this, "House Load FAILED", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean isActive() {
        return false;
    }


    private interface ProfileQuery {
        /**
         * The Projection.
         */
        String[] PROJECTION = {
                ContactsContract.CommonDataKinds.Email.ADDRESS,
                ContactsContract.CommonDataKinds.Email.IS_PRIMARY,
        };

        /**
         * The constant ADDRESS.
         */
        int ADDRESS = 0;
        /**
         * The constant IS_PRIMARY.
         */
        int IS_PRIMARY = 1;
    }
}

