package blankthings.chatapp.sections.chats.views;


import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import blankthings.chatapp.R;
import blankthings.chatapp.sections.chats.ChatItem;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 *  Describes a ChatItem view.
 *
 *  Created by iosif on 5/20/17.
 */
public class ChatCollectionViewHolder extends RecyclerView.ViewHolder {


    @BindView(R.id.chat_item_name_text) TextView lastUserTextView;
    @BindView(R.id.chat_item_last_text) TextView lastUserCommentTextView;
    @BindView(R.id.chat_item_date_text) TextView lastSentDateTextView;

    private ChatCollectionAdapter.OnChatItemClickListener onChatItemClickListener;


    public ChatCollectionViewHolder(View itemView,
                                    ChatCollectionAdapter.OnChatItemClickListener onItemClick) {
        super(itemView);
        onChatItemClickListener = onItemClick;
        ButterKnife.bind(this, itemView);
    }


    public void bind(final ChatItem chatItem) {
        if (chatItem == null) {
            return;
        }

        lastUserTextView.setText(chatItem.getUser());
        lastUserCommentTextView.setText(chatItem.getMessage());
        lastSentDateTextView.setText(chatItem.getDateReceived());

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onChatItemClickListener != null) {
                    onChatItemClickListener.onChatItemClicked(chatItem);
                }
            }
        });
    }
}
