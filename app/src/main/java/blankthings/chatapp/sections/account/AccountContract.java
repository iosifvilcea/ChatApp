package blankthings.chatapp.sections.account;

import blankthings.chatapp.BaseContract;

/**
 * Created by iosif on 5/20/17.
 */

public class AccountContract {


    public interface AccountPresenter extends BaseContract.BasePresenter {

        void loginButtonClicked(String email, String pass);
        void createButtonClicked(String email, String pass, String name);

        void createAccount();
        void signin();

    }


    public interface AccountView extends BaseContract.BaseView<AccountPresenter> {

        void startLoading();
        void stopLoading();

        void showEmailError(String error);
        void showPassError(String error);
        void showPassConfirmError(String error);
        void showNameError(String error);
        void clearErrors();

        void showEmailView();
        void showCreateView();

    }

}
