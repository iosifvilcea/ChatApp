package blankthings.chatapp.sections.chat;

import java.util.List;

import blankthings.chatapp.BaseContract;
import blankthings.chatapp.api.models.chats.ChatMessage;

/**
 * Created by iosif on 5/21/17.
 */

public class ChatContract {

    public interface ChatPresenter extends BaseContract.BasePresenter<ChatView> {

        void fetchMessages();

        void sendMessage(String message);

    }


    public interface ChatView extends BaseContract.BaseView {

        void populateMessages(List<ChatMessage> chatMessages);

        void populateMessage(ChatMessage chatMessage);

        void onMessageSubmit(String message);

    }

}
