package blankthings.chatapp.sections.chat.views;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.util.List;

import blankthings.chatapp.R;
import blankthings.chatapp.sections.chat.ChatContract;
import blankthings.chatapp.sections.chat.ChatPresenterImpl;
import blankthings.chatapp.sections.chats.ChatItem;
import blankthings.chatapp.utilities.ToolbarController;
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
    public ToolbarController toolbarController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_layout);

        ButterKnife.bind(this);
        setupToolbar();
        setupRecyclerView();
        setupPresenter();

        if (savedInstanceState != null) {
            // TODO: 5/21/17
        }
    }


    private void setupToolbar() {
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appBar);
        toolbarController = new ToolbarController(this, toolbar, appBarLayout);
    }


    @Override
    protected void onResume() {
        super.onResume();
        toolbarController.enableToolbarScroll(false);
        toolbarController.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final ChatItem chatItem = getIntent().getParcelableExtra(ChatItem.KEY);
        if (chatItem != null) {
            setTitle(chatItem.getUser());
        }
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
