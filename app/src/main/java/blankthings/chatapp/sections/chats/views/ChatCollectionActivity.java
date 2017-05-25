package blankthings.chatapp.sections.chats.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.util.List;

import blankthings.chatapp.R;
import blankthings.chatapp.api.models.account.Profile;
import blankthings.chatapp.api.models.chats.ChatMessage;
import blankthings.chatapp.sections.chat.views.ChatActivity;
import blankthings.chatapp.sections.chats.ChatCollectionContract;
import blankthings.chatapp.sections.chats.ChatCollectionPresenterImpl;
import blankthings.chatapp.utilities.ToolbarController;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by iosif on 5/20/17.
 */

public class ChatCollectionActivity
        extends AppCompatActivity
        implements ChatCollectionContract.ChatCollectionView {

    public static final String TAG = ChatCollectionActivity.class.getSimpleName();

    @BindView(R.id.content)
    FrameLayout content;

    @BindView(R.id.fab)
    FloatingActionButton floatingActionButton;

    @BindView(R.id.loading)
    View loadingView;

    private AlertDialog dialog;

    private ChatCollectionAdapter chatsAdapter;
    private ChatCollectionPresenterImpl chatsPresenter;

    public ToolbarController toolbarController;
    private Profile profile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_layout);

        ButterKnife.bind(this);
        setupToolbar();
        setupRecyclerView();
        setupPresenter();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        final MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout_menu_item:
                chatsPresenter.logoutClicked();
                break;
        }

        return true;
    }


    @Override
    protected void onResume() {
        super.onResume();
        getSupportActionBar().setLogo(R.drawable.app_logo);
        setTitle(R.string.app_name);
        floatingActionButton.setVisibility(View.VISIBLE);
    }


    @Override
    protected void onPause() {
        floatingActionButton.setVisibility(View.GONE);
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }

        super.onPause();
    }


    private void setupToolbar() {
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appBar);
        toolbarController = new ToolbarController(this, toolbar, appBarLayout);
        toolbarController.enableToolbarScroll(false);
    }


    private void setupPresenter() {
        final Bundle bundle = getIntent().getExtras();
        profile = (Profile) bundle.get(Profile.KEY);
        chatsPresenter = new ChatCollectionPresenterImpl(this, profile);
    }


    private void setupRecyclerView() {
        final RecyclerView recyclerView = new RecyclerView(this);
        content.addView(recyclerView);

        /** RecyclerView LayoutManager */
        final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        /** RecyclerView Adapter */
        chatsAdapter = new ChatCollectionAdapter(onChatItemClickListener);
        recyclerView.setAdapter(chatsAdapter);

        /** ChatItem Divider */
        final DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
    }


    @OnClick(R.id.fab)
    public void onFloatingButtonClick() {
        generateCreateMessageDialog();
    }


    private void generateCreateMessageDialog() {
        final LayoutInflater inflater = LayoutInflater.from(this);
        final View content = inflater.inflate(R.layout.chat_create_dialog, null);

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        dialog = builder
                .setView(content)
                .create();

        dialog.show();

        setupDialog(content);
    }


    private void setupDialog(final View content) {
        final EditText nameEditText = (EditText) content.findViewById(R.id.chat_create_name_edit_text);
        final EditText msgEditText = (EditText) content.findViewById(R.id.chat_create_message_edit_text);

        final View button = content.findViewById(R.id.chat_create_send_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name = nameEditText.getText().toString();
                final String message = msgEditText.getText().toString();
                if (dialog != null) {
                    dialog.dismiss();
                    chatsPresenter.createChat(name, message);
                }
            }
        });
    }


    @Override
    public void startLoading() {
        loadingView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                /** Intentionally block UI while loading. */
            }});
        loadingView.setVisibility(View.VISIBLE);
    }


    @Override
    public void stopLoading() {
        loadingView.setOnClickListener(null);
        loadingView.setVisibility(View.GONE);
    }


    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void populateChats(List<ChatMessage> chats) {
        chatsAdapter.setChatItems(chats);
    }


    @Override
    public void addChat(ChatMessage chatMessage) {
        chatsAdapter.addChatItem(chatMessage);
    }


    @Override
    public void navigateToSelectedChat(final ChatMessage message) {
        floatingActionButton.setVisibility(View.GONE);

        final Intent intent = new Intent(this, ChatActivity.class);
        intent.putExtra(Profile.KEY, profile);
        intent.putExtra(ChatMessage.KEY, message);
        startActivity(intent);
    }


    @Override
    public void navigateBack() {
        onBackPressed();
    }


    private final ChatCollectionAdapter.OnChatItemClickListener onChatItemClickListener =
            new ChatCollectionAdapter.OnChatItemClickListener() {
        @Override
        public void onChatItemClicked(ChatMessage chatMessage) {
            chatsPresenter.chatSelected(chatMessage);
        }
    };
}
