package blankthings.chatapp.sections.account.views;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import blankthings.chatapp.R;
import blankthings.chatapp.sections.account.AccountContract;
import blankthings.chatapp.sections.account.AccountPresenterImpl;
import blankthings.chatapp.sections.chats.views.ChatCollectionActivity;
import blankthings.chatapp.sections.profile.Profile;
import blankthings.chatapp.utilities.ToolbarController;
import blankthings.chatapp.utilities.Utils;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity
        extends AppCompatActivity
        implements AccountContract.AccountView {

    public static final String TAG = LoginActivity.class.getSimpleName();

    private AccountContract.AccountPresenter presenter;
    private boolean isLoginState = true;

    @BindView(R.id.loading) View loadingView;

    @BindView(R.id.account_name_text_input) TextInputLayout nameTextInput;
    @BindView(R.id.account_name_edit_text) EditText nameEditText;

    @BindView(R.id.account_email_text_input) TextInputLayout emailTextInput;
    @BindView(R.id.account_email_edit_text) EditText emailEditText;

    @BindView(R.id.account_pass_text_input) TextInputLayout passTextInput;
    @BindView(R.id.account_pass_edit_text) EditText passEditText;

    @BindView(R.id.account_pass_confirm_text_input) TextInputLayout passConfirmTextInput;
    @BindView(R.id.account_pass_confirm_edit_text) EditText passConfirmEditText;

    @BindView(R.id.account_create_button) Button createButton;
    @BindView(R.id.account_submit_button) Button loginButton;


    public ToolbarController toolbarController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_layout);
        setupToolbar();
        setupViews();
        ButterKnife.bind(this);
        presenter = new AccountPresenterImpl(this);
    }


    private void setupToolbar() {
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appBar);
        toolbarController = new ToolbarController(this, toolbar, appBarLayout);
        toolbarController.enableToolbarScroll(false);
    }


    @Override
    protected void onResume() {
        super.onResume();
        getSupportActionBar().setLogo(R.drawable.app_logo);
        setTitle(R.string.app_name);
    }


    private void setupViews() {
        final FrameLayout container = (FrameLayout) findViewById(R.id.content);
        final LayoutInflater inflater = LayoutInflater.from(this);
        inflater.inflate(R.layout.login_activity, container);
    }


    @OnClick(R.id.account_create_button)
    public void onCreateAccountClicked() {
        if (isLoginState) {
            showCreateView();

        } else {
            presenter.createButtonClicked(
                    emailEditText.getText().toString(),
                    passEditText.getText().toString(),
                    passConfirmEditText.getText().toString(),
                    nameEditText.getText().toString()
            );
        }
    }


    @OnClick(R.id.account_submit_button)
    public void onSignInClicked() {
        Utils.hideKeyboard(this);
        if (isLoginState) {
            presenter.loginButtonClicked(
                    emailEditText.getText().toString(),
                    passEditText.getText().toString()
            );

        } else {
            showLoginView();
        }
    }


    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }


    @Override
    public void startLoading() {
        loadingView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /** Intentionally block UI while loading. */
            }
        });
        loadingView.setVisibility(View.VISIBLE);
    }


    @Override
    public void stopLoading() {
        loadingView.setOnClickListener(null);
        loadingView.setVisibility(View.GONE);
    }


    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void showEmailError(String error) {
        emailTextInput.setError(error);
        emailEditText.requestFocus();
    }


    @Override
    public void showPassError(String error) {
        passTextInput.setError(error);
        passEditText.requestFocus();
    }


    @Override
    public void showPassConfirmError(String error) {
        passConfirmTextInput.setError(error);
        passConfirmEditText.requestFocus();
    }


    @Override
    public void showNameError(String error) {
        nameTextInput.setError(error);
        nameEditText.requestFocus();
    }


    @Override
    public void clearErrors() {
        emailTextInput.setError(null);
        passTextInput.setError(null);
        passConfirmTextInput.setError(null);
        nameTextInput.setError(null);
    }


    @Override
    public void showLoginView() {
        showLogin(true);
    }


    @Override
    public void showCreateView() {
        showLogin(false);
    }


    private void showLogin(final boolean show) {
        isLoginState = show;
        if (isLoginState) {
            setupLoginStateViews();

        } else {
            setupCreateStateViews();
        }
    }


    private void setupLoginStateViews() {
        final int white = ContextCompat.getColor(this, android.R.color.white);
        final int accent = ContextCompat.getColor(this, R.color.colorAccent);
        final int transparent = ContextCompat.getColor(this, android.R.color.transparent);

        nameTextInput.setVisibility(View.GONE);
        passConfirmTextInput.setVisibility(View.GONE);

        loginButton.setText(R.string.account_button_sign_in);
        loginButton.setTextColor(white);
        loginButton.setBackgroundColor(accent);

        createButton.setTextColor(accent);
        createButton.setBackgroundColor(transparent);
    }


    private void setupCreateStateViews() {
        final int white = ContextCompat.getColor(this, android.R.color.white);
        final int accent = ContextCompat.getColor(this, R.color.colorAccent);
        final int transparent = ContextCompat.getColor(this, android.R.color.transparent);

        nameTextInput.setVisibility(View.VISIBLE);
        passConfirmTextInput.setVisibility(View.VISIBLE);

        loginButton.setText(R.string.app_cancel);
        loginButton.setTextColor(accent);
        loginButton.setBackgroundColor(transparent);

        createButton.setTextColor(white);
        createButton.setBackgroundColor(accent);
    }


    @Override
    public void navigateToChats(Profile profile) {
        final Intent intent = new Intent(this, ChatCollectionActivity.class);
        final Bundle bundle = new Bundle();
        bundle.putParcelable(Profile.KEY, profile);
        intent.putExtras(bundle);
        startActivity(intent);
    }


    @Override
    public void navigateBack() {
        onBackPressed();
    }

}
