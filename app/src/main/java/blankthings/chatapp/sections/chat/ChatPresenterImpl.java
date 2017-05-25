package blankthings.chatapp.sections.chat;

import android.util.Log;

import blankthings.chatapp.api.ApiService;
import blankthings.chatapp.api.models.chats.ChatMessage;
import blankthings.chatapp.api.models.chats.Messages;
import blankthings.chatapp.sections.profile.Profile;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by iosif on 5/21/17.
 */

public class ChatPresenterImpl implements ChatContract.ChatPresenter {

    private ApiService apiService;
    private ChatContract.ChatView view;

    private Profile profile;
    private ChatMessage selectedMessage;

    public ChatPresenterImpl(final ChatContract.ChatView view, final Profile profile, final ChatMessage chatMessage) {
        apiService = new ApiService();
        this.profile = profile;
        this.selectedMessage = chatMessage;
        onAttach(view);
        start();
    }


    @Override
    public void fetchMessages() {
        view.startLoading();
        apiService.fetchChatMessages(profile.getAuth(), selectedMessage.getChatId(), 1, 50,
                new Callback<Messages>() {
            @Override
            public void onResponse(Call<Messages> call, Response<Messages> response) {
                view.stopLoading();
                final Messages messageList = response.body();
                if (messageList.getMessages() != null && messageList.getMessages().size() > 0) {
                    view.populateMessages(messageList.getMessages());
                } else {
                    view.showError("Oh no! We can't find your messages!");
                }
            }

            @Override
            public void onFailure(Call<Messages> call, Throwable t) {
                view.stopLoading();
                view.showError("Oh no! Something went wrong.");
                view.navigateBack();
            }
        });
    }


    @Override
    public void sendMessage(final String message) {
        Log.e(ChatPresenterImpl.class.getSimpleName(), message);
        apiService.sendMessage(message, profile.getAuth(), selectedMessage.getChatId(),
                new Callback<ChatMessage>() {
            @Override
            public void onResponse(Call<ChatMessage> call, Response<ChatMessage> response) {
                // Success.
                view.populateMessage(response.body());
            }

            @Override
            public void onFailure(Call<ChatMessage> call, Throwable t) {
                Log.e(ChatPresenterImpl.class.getSimpleName(), t.getMessage());
            }
        });
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
