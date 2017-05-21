package blankthings.chatapp.sections.account;

import android.text.TextUtils;

import java.util.regex.Pattern;

import blankthings.chatapp.utilities.Utils;

/**
 * Created by iosif on 5/20/17.
 */

public class AccountPresenterImpl implements AccountContract.AccountPresenter {

    public static final String TAG = AccountPresenterImpl.class.getSimpleName();

    private AccountContract.AccountView view;


    public AccountPresenterImpl(final AccountContract.AccountView accountView) {
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
            signin();
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
            createAccount();
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
    public void createAccount() {
        // TODO: 5/20/17 Make Request.
    }


    @Override
    public void signin() {
        // TODO: 5/20/17 Make Request.

        view.navigateToChats();
    }


    @Override
    public void onAttach(AccountContract.AccountView view) {
        this.view = Utils.checkNotNull(view);
    }


    @Override
    public void onDetach() {}
}
