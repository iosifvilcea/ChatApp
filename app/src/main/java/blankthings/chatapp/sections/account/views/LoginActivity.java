package blankthings.chatapp.sections.account.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import blankthings.chatapp.R;
import blankthings.chatapp.sections.Utils;
import blankthings.chatapp.sections.account.AccountContract;

public class LoginActivity
        extends AppCompatActivity
        implements AccountContract.AccountView {


    private AccountContract.AccountPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_layout);
    }


    @Override
    public void setPresenter(AccountContract.AccountPresenter presenter) {
        this.presenter = Utils.checkNotNull(presenter);
    }


    @Override
    public void startLoading() {

    }


    @Override
    public void stopLoading() {

    }


    @Override
    public void showEmailError(String error) {

    }


    @Override
    public void showPassError(String error) {

    }


    @Override
    public void showPassConfirmError(String error) {

    }


    @Override
    public void showNameError(String error) {

    }


    @Override
    public void clearErrors() {

    }


    @Override
    public void showEmailView() {

    }


    @Override
    public void showCreateView() {

    }
}
