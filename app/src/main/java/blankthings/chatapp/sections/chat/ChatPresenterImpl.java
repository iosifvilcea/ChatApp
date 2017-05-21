package blankthings.chatapp.sections.chat;

import java.util.ArrayList;
import java.util.List;

import blankthings.chatapp.sections.chats.ChatItem;
import blankthings.chatapp.utilities.Utils;

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

        view.stopLoading();

        final List list = generateMessages();
        view.populateMessages(list);
    }

    private List<ChatItem> generateMessages() {
        final List list = new ArrayList();
        list.add(new ChatItem(0, "Blake", "Hoop today?", "Mon, 21", false));
        list.add(new ChatItem(0, "You", "Yes, let's do it!", "Mon, 21", true));
        list.add(new ChatItem(0, "Blake", "Sweet, see you there!", "Mon, 21", false));
        return list;
    }


    @Override
    public void sendMessage(String message) {
        view.startLoading();

        // TODO: 5/21/17

        final ChatItem item = new ChatItem(0, "You", message, Utils.getTodaysFormattedDate(), true);
        view.populateMessage(item);
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
