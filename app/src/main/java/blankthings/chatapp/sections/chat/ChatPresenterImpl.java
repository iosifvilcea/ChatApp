package blankthings.chatapp.sections.chat;

/**
 * Created by iosif on 5/21/17.
 */

public class ChatPresenterImpl implements ChatContract.ChatPresenter {

    private ChatContract.ChatView view;

    public ChatPresenterImpl(ChatContract.ChatView view) {
        onAttach(view);
        start();
    }


    @Override
    public void fetchMessages() {
        view.startLoading();

        // TODO: 5/21/17
    }


    @Override
    public void sendMessage(String message) {
        view.startLoading();

        // TODO: 5/21/17
    }


    @Override
    public void onAttach(ChatContract.ChatView view) {
        this.view = view;
    }


    @Override
    public void onDetach() {}


    @Override
    public void start() {
        fetchMessages();
    }
}
