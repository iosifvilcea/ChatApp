package blankthings.chatapp.api.models.chats;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by iosif on 5/24/17.
 */

public class Messages {


    @SerializedName("data")
    private List<ChatMessage> data = null;


    @SerializedName("meta")
    private Meta meta;


    public List<ChatMessage> getMessages() {
        return data;
    }


    public void setMessages(List<ChatMessage> data) {
        this.data = data;
    }


    public Meta getMeta() {
        return meta;
    }


    public void setMeta(Meta meta) {
        this.meta = meta;
    }

}
