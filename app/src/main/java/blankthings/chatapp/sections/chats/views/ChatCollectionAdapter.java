package blankthings.chatapp.sections.chats.views;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import blankthings.chatapp.R;
import blankthings.chatapp.api.models.chats.ChatMessage;

/**
 * Created by iosif on 5/20/17.
 */

public class ChatCollectionAdapter extends RecyclerView.Adapter<ChatCollectionViewHolder> {

    public static final String TAG = ChatCollectionAdapter.class.getSimpleName();

    private List<ChatMessage> chatItems = new ArrayList<>();
    private OnChatItemClickListener onItemClickListener;


    public ChatCollectionAdapter(OnChatItemClickListener listener) {
        onItemClickListener = listener;
    }


    public void setChatItems(List<ChatMessage> chatItems) {
        if (chatItems == null || chatItems.isEmpty()) {
            return;
        }

        this.chatItems.clear();
        this.chatItems.addAll(chatItems);
        notifyDataSetChanged();
    }


    public void addChatItem(ChatMessage chatItem) {
        if (chatItem == null) {
            return;
        }

        this.chatItems.add(0, chatItem);
        notifyItemInserted(0);
    }


    @Override
    public ChatCollectionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final Context context = parent.getContext();
        final LayoutInflater inflater = LayoutInflater.from(context);

        final View view = inflater.inflate(R.layout.chat_item_preview_layout, parent, false);
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
        void onChatItemClicked(ChatMessage childItem);
    }
}
