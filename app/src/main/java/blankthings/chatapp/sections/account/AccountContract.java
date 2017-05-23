package blankthings.chatapp.sections.account;

import blankthings.chatapp.BaseContract;
import blankthings.chatapp.sections.profile.Profile;

/**
 * Created by iosif on 5/20/17.
 */

public class AccountContract {


    public interface AccountPresenter extends BaseContract.BasePresenter<AccountView> {

        /** Events */
        void loginButtonClicked(String email, String pass);
        void createButtonClicked(String email, String pass, String passConfirm, String name);

        /** Actions */
        void createAccount(String email, String pass, String name);
        void signin(String email, String password);

    }


    public interface AccountView extends BaseContract.BaseView {

        void showEmailError(String error);
        void showPassError(String error);
        void showPassConfirmError(String error);
        void showNameError(String error);
        void clearErrors();

        void showLoginView();
        void showCreateView();

        void navigateToChats(Profile profile);

    }

}
