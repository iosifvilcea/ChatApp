package blankthings.chatapp.sections.chats;

import blankthings.chatapp.utilities.Utils;

/**
 * Created by iosif on 5/20/17.
 */

public class ChatCollectionPresenterImpl
        implements ChatCollectionContract.ChatCollectionPresenter {

    private ChatCollectionContract.ChatCollectionView view;


    public ChatCollectionPresenterImpl(ChatCollectionContract.ChatCollectionView view) {
        onAttach(view);
        start();
    }


    @Override
    public void onAttach(ChatCollectionContract.ChatCollectionView view) {
        this.view = Utils.checkNotNull(view);
    }


    @Override
    public void onDetach() {

    }


    @Override
    public void start() {
        fetchChats();
    }


    @Override
    public void fetchChats() {
        view.startLoading();
    }


    @Override
    public void createChat() {

    }


    @Override
    public void chatSelected(ChatItem chatItem) {

    }
}
