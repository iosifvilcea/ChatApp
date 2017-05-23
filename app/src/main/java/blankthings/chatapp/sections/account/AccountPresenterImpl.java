package blankthings.chatapp.sections.account;

import android.text.TextUtils;
import android.util.Log;

import java.util.regex.Pattern;

import blankthings.chatapp.api.AccountData;
import blankthings.chatapp.api.ApiService;
import blankthings.chatapp.sections.profile.Profile;
import blankthings.chatapp.utilities.Utils;
import okhttp3.Headers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by iosif on 5/20/17.
 */

public class AccountPresenterImpl implements AccountContract.AccountPresenter {

    public static final String TAG = AccountPresenterImpl.class.getSimpleName();

    private AccountContract.AccountView view;

    private ApiService apiService;
    private Profile profile;

    public AccountPresenterImpl(final AccountContract.AccountView accountView) {
        apiService = new ApiService();

        onAttach(accountView);
        start();
    }


    @Override
    public void start() {
        view.showLoginView();
    }


    @Override
    public void loginButtonClicked(final String email, final String pass) {
        view.clearErrors();
        final boolean isValid = validateLogin(email, pass);
        if (isValid) {
            signin(email, pass);
        }
    }


    private boolean validateLogin(String email, String pass) {
        if (TextUtils.isEmpty(email)) {
            final String err = "Email field cannot be empty.";
            view.showEmailError(err);
            return false;
        }

        final Pattern emailPattern = android.util.Patterns.EMAIL_ADDRESS;
        if (!emailPattern.matcher(email).matches()) {
            final String err = "Please enter a valid address.";
            view.showEmailError(err);
            return false;
        }

        if (TextUtils.isEmpty(pass)) {
            final String err = "Password field cannot be empty.";
            view.showPassError(err);
            return false;
        }

        return true;
    }


    @Override
    public void createButtonClicked(final String email, final String pass, final String passConfirm, final String name) {
        final boolean isValid = validateCreateAccount(email, pass, passConfirm, name);
        if (isValid) {
            createAccount(email, pass, name);
        }
    }


    private boolean validateCreateAccount(String email, String pass, String passConfirm, String name) {
        view.clearErrors();
        final boolean isBasicLoginValid = validateLogin(email, pass);
        if (!isBasicLoginValid) {
            return false;
        }

        if (TextUtils.isEmpty(name)) {
            final String err = "Name field cannot be empty.";
            view.showNameError(err);
            return false;
        }

        if (!pass.equals(passConfirm)) {
            final String err = "Password does not match.";
            view.showPassConfirmError(err);
            return false;
        }

        return true;
    }


    @Override
    public void createAccount(String email, String pass, String name) {
        view.startLoading();
    }


    @Override
    public void signin(final String email, final String password) {
        view.startLoading();
        apiService.getLogin(email, password, signinCallback);
    }


    @Override
    public void onAttach(AccountContract.AccountView view) {
        this.view = Utils.checkNotNull(view);
    }


    @Override
    public void onDetach() {}


    /**
     * Signin Callback
     */
    private Callback<AccountData> signinCallback = new Callback<AccountData>() {

        @Override
        public void onResponse(Call<AccountData> call, Response<AccountData> response) {
            view.stopLoading();

            final String authorization = getAuthorization(response);
            Log.e(TAG, authorization);

            final AccountData accountData = response.body();
            final int id = accountData.getId();
            final String email = accountData.getEmail();
            final String name = accountData.getName();

            profile = new Profile(id, name, email, authorization);
            view.navigateToChats(profile);
        }


        @Override
        public void onFailure(Call<AccountData> call, Throwable t) {
            view.stopLoading();
            view.showError("Oh no! We're unable to login at this time.");
        }


        String getAuthorization(Response<AccountData> response) {
            Headers headers = response.headers();
            return headers.get("Authorization");
        }
    };
}
