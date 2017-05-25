package blankthings.chatapp.sections.chat.views;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import blankthings.chatapp.R;
import blankthings.chatapp.api.models.account.Profile;
import blankthings.chatapp.api.models.chats.ChatMessage;
import blankthings.chatapp.sections.chat.ChatContract;
import blankthings.chatapp.sections.chat.ChatPresenterImpl;
import blankthings.chatapp.utilities.ToolbarController;
import blankthings.chatapp.utilities.Utils;
import butterknife.BindView;
import butterknife.ButterKnife;

import static android.view.View.GONE;

/**
 * Created by iosif on 5/20/17.
 */

public class ChatActivity extends AppCompatActivity implements ChatContract.ChatView {

    private static final String CHAT_MSG_KEY = "CHAT_MESSAGES_KEY";

    @BindView(R.id.chat_create_name_edit_text)
    EditText nameEditText;

    @BindView(R.id.chat_create_message_edit_text)
    EditText messageEditText;

    @BindView(R.id.chat_create_send_button)
    FloatingActionButton sendButton;

    @BindView(R.id.chat_activity_recycler)
    RecyclerView recyclerView;

    @BindView(R.id.loading)
    View loadingView;

    private ChatAdapter chatAdapter;
    private ChatPresenterImpl presenter;
    public ToolbarController toolbarController;
    private ArrayList<ChatMessage> messages;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_layout);
        final LayoutInflater inflater = LayoutInflater.from(this);
        inflater.inflate(R.layout.chat_activity_layout, (FrameLayout)findViewById(R.id.content));

        ButterKnife.bind(this);
        setupToolbar();
        setupViews();
        setupRecyclerView();

        final Profile profile = getIntent().getParcelableExtra(Profile.KEY);
        final ChatMessage selectedMessage = getIntent().getParcelableExtra(ChatMessage.KEY);
        setupPresenter(profile, selectedMessage);

        if (savedInstanceState != null) {
            messages = savedInstanceState.getParcelableArrayList(CHAT_MSG_KEY);
            populateMessages(messages);
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(CHAT_MSG_KEY, messages);
    }


    @Override
    protected void onResume() {
        super.onResume();
        toolbarController.enableToolbarScroll(false);
        toolbarController.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final ChatMessage chatMessage = getIntent().getParcelableExtra(ChatMessage.KEY);
        if (chatMessage != null) {
            setTitle(chatMessage.getUser().getName());
        }
    }


    private void setupToolbar() {
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appBar);
        toolbarController = new ToolbarController(this, toolbar, appBarLayout);
    }


    private void setupRecyclerView() {
        /** RecyclerView LayoutManager */
        final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        /** RecyclerView Adapter */
        chatAdapter = new ChatAdapter();
        recyclerView.setAdapter(chatAdapter);
    }


    private void setupViews() {
        nameEditText.setVisibility(GONE);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.hideKeyboard(v, getApplicationContext());
                final String message = messageEditText.getText().toString();
                messageEditText.setText(null);
                if (presenter != null) {
                    presenter.sendMessage(message);
                }
            }
        });
    }


    private void setupPresenter(final Profile profile, final ChatMessage chatMessage) {
        presenter = new ChatPresenterImpl(this, profile, chatMessage);
    }


    @Override
    public void startLoading() {
        loadingView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                /** Intentionally Block UI while loading. */
            }});
        loadingView.setVisibility(View.VISIBLE);
    }


    @Override
    public void stopLoading() {
        loadingView.setOnClickListener(null);
        loadingView.setVisibility(View.GONE);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
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
    public void populateMessages(List<ChatMessage> chatMessages) {
        chatAdapter.setMessages(chatMessages);
    }


    @Override
    public void populateMessage(ChatMessage chatMessage) {
        chatAdapter.addMessage(chatMessage);
    }


    @Override
    public void onMessageSubmit(String message) {
        presenter.sendMessage(message);
    }
}
