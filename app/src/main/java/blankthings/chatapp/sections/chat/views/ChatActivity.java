package blankthings.chatapp.sections.chat.views;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import blankthings.chatapp.R;
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

    @BindView(R.id.chat_create_name_edit_text)
    EditText nameEditText;

    @BindView(R.id.chat_create_message_edit_text)
    EditText messageEditText;

    @BindView(R.id.chat_create_send_button)
    FloatingActionButton sendButton;

    @BindView(R.id.chat_activity_recycler)
    RecyclerView recyclerView;

    @BindView(R.id.chat_loading)
    View loadingView;

    private ChatAdapter chatAdapter;
    private ChatPresenterImpl presenter;
    public ToolbarController toolbarController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_activity);

        ButterKnife.bind(this);
        setupToolbar();
        setupViews();
        setupRecyclerView();
        setupPresenter();

        if (savedInstanceState != null) {
            // TODO: 5/21/17
        }
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


    private void setupPresenter() {
        presenter = new ChatPresenterImpl(this);
    }


    @Override
    public void startLoading() {
        loadingView.setVisibility(View.VISIBLE);
    }


    @Override
    public void stopLoading() {
        loadingView.setVisibility(View.GONE);
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
