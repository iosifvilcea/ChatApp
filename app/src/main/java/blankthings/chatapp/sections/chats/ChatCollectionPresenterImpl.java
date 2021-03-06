package blankthings.chatapp.sections.chats;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import blankthings.chatapp.api.ApiService;
import blankthings.chatapp.api.models.account.Profile;
import blankthings.chatapp.api.models.chats.Chat;
import blankthings.chatapp.api.models.chats.ChatMessage;
import blankthings.chatapp.api.models.chats.User;
import blankthings.chatapp.api.models.chats.UserData;
import blankthings.chatapp.utilities.Utils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by iosif on 5/20/17.
 */

public class ChatCollectionPresenterImpl
        implements ChatCollectionContract.ChatCollectionPresenter {

    public static final String TAG = ChatCollectionPresenterImpl.class.getSimpleName();

    private ChatCollectionContract.ChatCollectionView view;
    private ApiService apiService;
    private Profile profile;
    private List<UserData> lastRetrievedChatData;


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
        apiService.fetchChats(profile.getAuth(), 1, 50, chatListCallback);
    }


    @Override
    public void logoutClicked() {
        apiService.logout(profile.getAuth());
        view.navigateBack();
    }


    @Override
    public void createChat(final String name, final String message) {
        final ChatMessage chat = new ChatMessage();
        chat.setUser(new User() {{ setName(name); }});
        chat.setMessage(message);
        chat.setOutBound(true);

        view.addChat(chat);
    }


    @Override
    public void chatSelected(ChatMessage chatItem) {
        view.navigateToSelectedChat(chatItem);
    }


    private List<ChatMessage> parseMessages(final Response<Chat> response) {
        final Chat chat = response.body();
        lastRetrievedChatData = chat.getData();

        final List<ChatMessage> messages = new ArrayList<>();
        for (UserData userData : chat.getData()) {
            messages.add(userData.getChatMessage());
        }

        return messages;
    }


    private Callback<Chat> chatListCallback = new Callback<Chat>() {
        @Override
        public void onResponse(Call<Chat> call, Response<Chat> response) {
            view.stopLoading();
            view.populateChats(parseMessages(response));
        }

        @Override
        public void onFailure(Call<Chat> call, Throwable t) {
            view.stopLoading();
            Log.e(TAG, t.getMessage());
        }
    };
}
