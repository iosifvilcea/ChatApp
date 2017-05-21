package blankthings.chatapp.sections.chats.views;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import blankthings.chatapp.R;
import blankthings.chatapp.sections.chats.ChatItem;

/**
 * Created by iosif on 5/20/17.
 */

public class ChatCollectionAdapter extends RecyclerView.Adapter<ChatCollectionViewHolder> {

    public static final String TAG = ChatCollectionAdapter.class.getSimpleName();

    private List<ChatItem> chatItems = new ArrayList<>();
    private OnChatItemClickListener onItemClickListener;


    public ChatCollectionAdapter(OnChatItemClickListener listener) {
        onItemClickListener = listener;
    }


    public void setChatItems(List<ChatItem> chatItems) {
        if (chatItems == null || chatItems.isEmpty()) {
            return;
        }

        this.chatItems.addAll(chatItems);
        notifyDataSetChanged();
    }


    @Override
    public ChatCollectionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final Context context = parent.getContext();
        final LayoutInflater inflater = LayoutInflater.from(context);

        final View view = inflater.inflate(R.layout.chat_item_layout, parent, false);
        return new ChatCollectionViewHolder(view, onItemClickListener);
    }


    @Override
    public void onBindViewHolder(ChatCollectionViewHolder holder, int position) {
        holder.bind(chatItems.get(position));
    }


    @Override
    public int getItemCount() {
        return chatItems.size();
    }


    public interface OnChatItemClickListener {
        void onChatItemClicked(ChatItem childItem);
    }
}
