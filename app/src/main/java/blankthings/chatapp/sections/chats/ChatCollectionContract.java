package blankthings.chatapp.sections.chats;

import java.util.List;

import blankthings.chatapp.BaseContract;

/**
 * Created by iosif on 5/20/17.
 */

public class ChatCollectionContract {

    public interface ChatCollectionPresenter extends BaseContract.BasePresenter<ChatCollectionView> {

        void fetchChats();

        void createChat();

        void chatSelected(ChatItem chatItem);

    }


    public interface ChatCollectionView extends BaseContract.BaseView {

        void populateChats(List<ChatItem> chats);

        void navigateToSelectedChat(ChatItem chatItem);

    }

}
