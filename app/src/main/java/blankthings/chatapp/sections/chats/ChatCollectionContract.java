package blankthings.chatapp.sections.chats;

import java.util.List;

import blankthings.chatapp.BaseContract;
import blankthings.chatapp.api.models.chats.ChatMessage;

/**
 * Created by iosif on 5/20/17.
 */

public class ChatCollectionContract {

    public interface ChatCollectionPresenter extends BaseContract.BasePresenter<ChatCollectionView> {

        void fetchChats();

        void createChat(String name, String message);

        void chatSelected(ChatMessage chatMessage);

        void logoutClicked();

    }


    public interface ChatCollectionView extends BaseContract.BaseView {

        void populateChats(List<ChatMessage> chats);

        void addChat(ChatMessage chatMessage);

        void navigateToSelectedChat(ChatMessage selectedChatItem);

    }

}
