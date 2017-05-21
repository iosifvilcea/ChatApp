package blankthings.chatapp.sections.chats.views;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import blankthings.chatapp.R;
import blankthings.chatapp.sections.chat.ChatActivity;
import blankthings.chatapp.sections.chats.ChatCollectionContract;
import blankthings.chatapp.sections.chats.ChatCollectionPresenterImpl;
import blankthings.chatapp.sections.chats.ChatItem;
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

    @BindView(R.id.content) FrameLayout content;
    @BindView(R.id.fab) FloatingActionButton floatingActionButton;

    private Dialog dialog;

    private ChatCollectionAdapter chatsAdapter;
    private ChatCollectionPresenterImpl chatsPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_layout);

        ButterKnife.bind(this);
        setupRecyclerView();
        setupPresenter();
    }


    private void setupPresenter() {
        chatsPresenter = new ChatCollectionPresenterImpl(this);
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
                new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        generateMockData();
    }

    private void generateMockData() {
        List<ChatItem> list = new ArrayList<>();
        list.add(new ChatItem(0, "Joe", "Hey dude, what's up?", "Thurs, 26"));
        list.add(new ChatItem(1, "Mom", "ok, see you soon!", "Thurs, 26"));
        list.add(new ChatItem(2, "Jen", "Hahha", "Friday, 27"));
        chatsAdapter.setChatItems(list);
    }


    @OnClick(R.id.fab)
    public void onFloatingButtonClick() {
        dialog = new Dialog(this);
        final TextView textView = new TextView(this);
        textView.setText("HEY");
        dialog.setContentView(textView);
        dialog.show();
    }


    @Override
    public void startLoading() {
        // TODO: 5/20/17
    }


    @Override
    public void stopLoading() {
        // TODO: 5/20/17
    }


    @Override
    protected void onPause() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }

        super.onPause();
    }


    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void populateChats(List<ChatItem> chats) {
        chatsAdapter.setChatItems(chats);
    }


    @Override
    public void navigateToSelectedChat(ChatItem chatItem) {
        final Intent intent = new Intent(this, ChatActivity.class);
        final Bundle bundle = new Bundle();
        bundle.putParcelable(ChatItem.KEY, chatItem);
        intent.putExtras(bundle);
    }


    @Override
    public void navigateBack() {
        onBackPressed();
    }


    private ChatCollectionAdapter.OnChatItemClickListener onChatItemClickListener =
            new ChatCollectionAdapter.OnChatItemClickListener() {
        @Override
        public void onChatItemClicked(ChatItem childItem) {
            chatsPresenter.chatSelected(childItem);
        }
    };
}
