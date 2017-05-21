package blankthings.chatapp.sections.chat.views;

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
 * Created by iosif on 5/21/17.
 */

public class ChatAdapter extends RecyclerView.Adapter<ChatViewHolder> {

    private final static int TYPE_OUTBOUND = 0;
    private final static int TYPE_INBOUND = 1;

    private List<ChatItem> messages;


    public ChatAdapter() {
        messages = new ArrayList<>();
    }


    public void setMessages(List<ChatItem> items) {
        if (items == null || items.isEmpty()) {
            return;
        }

        messages.clear();
        messages.addAll(items);
        notifyDataSetChanged();
    }


    public void addMessage(ChatItem item) {
        if (item == null) {
            return;
        }

        messages.add(item);
        notifyItemInserted(messages.size()-1);
    }


    @Override
    public int getItemViewType(int position) {
        final boolean isMessageBeingSentOut = messages.get(position).isOutBound();
        return (isMessageBeingSentOut) ? TYPE_OUTBOUND : TYPE_INBOUND;
    }


    @Override
    public ChatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final Context context = parent.getContext();
        final LayoutInflater inflater = LayoutInflater.from(context);

        final View view;
        switch (viewType) {
            case TYPE_OUTBOUND:
                view = inflater.inflate(R.layout.chat_item_outgoing_layout, parent, false);
                break;

            case TYPE_INBOUND:
            default:
                view = inflater.inflate(R.layout.chat_item_incoming_layout, parent, false);
                break;
        }

        return new ChatViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ChatViewHolder holder, int position) {
        holder.bind(messages.get(position));
    }


    @Override
    public int getItemCount() {
        return messages.size();
    }
}
