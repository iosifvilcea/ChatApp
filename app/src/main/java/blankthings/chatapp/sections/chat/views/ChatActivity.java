package blankthings.chatapp.sections.chat.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.util.List;

import blankthings.chatapp.R;
import blankthings.chatapp.sections.chat.ChatContract;
import blankthings.chatapp.sections.chat.ChatPresenterImpl;
import blankthings.chatapp.sections.chats.ChatItem;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by iosif on 5/20/17.
 */

public class ChatActivity extends AppCompatActivity implements ChatContract.ChatView {

    @BindView(R.id.content)
    FrameLayout content;

    private ChatAdapter chatAdapter;
    private ChatPresenterImpl presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_layout);

        ButterKnife.bind(this);
        setupRecyclerView();
        setupPresenter();
    }


    private void setupRecyclerView() {
        final RecyclerView recyclerView = new RecyclerView(this);
        content.addView(recyclerView);

        /** RecyclerView LayoutManager */
        final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        /** RecyclerView Adapter */
        chatAdapter = new ChatAdapter();
        recyclerView.setAdapter(chatAdapter);
    }


    private void setupPresenter() {
        presenter = new ChatPresenterImpl(this);
    }


    @Override
    public void startLoading() {
        // TODO: 5/21/17
    }


    @Override
    public void stopLoading() {
        // TODO: 5/21/17
    }


    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void navigateBack() {
        onBackPressed();
    }


    @Override
    public void populateMessages(List<ChatItem> chatItems) {
        chatAdapter.setMessages(chatItems);
    }


    @Override
    public void onMessageSubmit(String message) {
        presenter.sendMessage(message);
    }
}
