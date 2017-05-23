package blankthings.chatapp.sections.chats;

import blankthings.chatapp.api.ApiService;
import blankthings.chatapp.sections.profile.Profile;
import blankthings.chatapp.utilities.Utils;

/**
 * Created by iosif on 5/20/17.
 */

public class ChatCollectionPresenterImpl
        implements ChatCollectionContract.ChatCollectionPresenter {

    private ChatCollectionContract.ChatCollectionView view;
    private ApiService apiService;
    private Profile profile;


    public ChatCollectionPresenterImpl(final ChatCollectionContract.ChatCollectionView view,
                                       final Profile profile) {

        apiService = new ApiService();
        this.profile = profile;
        onAttach(view);
        start();
    }


    @Override
    public void onAttach(final ChatCollectionContract.ChatCollectionView view) {
        this.view = Utils.checkNotNull(view);
    }


    @Override
    public void onDetach() {}


    @Override
    public void start() {
        fetchChats();
    }


    @Override
    public void fetchChats() {
        view.startLoading();
    }


    @Override
    public void logoutClicked() {
        apiService.logout(profile.getAuth());
        view.navigateBack();
    }


    @Override
    public void createChat(final String name, final String message) {
        // TODO: 5/21/17 create chat request.

        final ChatItem chatItem = new ChatItem(0, name, message, Utils.getTodaysFormattedDate(), true);
        view.addChat(chatItem);
    }


    @Override
    public void chatSelected(ChatItem chatItem) {
        view.navigateToSelectedChat(chatItem);
    }
}
