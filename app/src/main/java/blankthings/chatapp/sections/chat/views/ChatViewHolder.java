package blankthings.chatapp.sections.chat.views;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import blankthings.chatapp.R;
import blankthings.chatapp.sections.chats.ChatItem;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by iosif on 5/21/17.
 */

public class ChatViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.chat_item_message_date_text_view)
    TextView dateTextView;

    @BindView(R.id.chat_item_message_text_view)
    TextView messageText;


    public ChatViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }


    public void bind(ChatItem chatItem) {
        dateTextView.setText(chatItem.getDate());
        messageText.setText(chatItem.getMessage());
    }
}
