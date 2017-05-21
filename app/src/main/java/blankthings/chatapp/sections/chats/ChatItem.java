package blankthings.chatapp.sections.chats;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by iosif on 5/20/17.
 */

public class ChatItem implements Parcelable {

    public static final String KEY = "CHAT_ITEM_KEY";

    private int id;
    private String lastUser;
    private String lastUserComment;
    private String lastSent;

    public ChatItem(int id, String lastUser, String lastUserComment, String lastSent) {
        this.id = id;
        this.lastUser = lastUser;
        this.lastUserComment = lastUserComment;
        this.lastSent = lastSent;
    }


    public int getId() {
        return id;
    }


    public String getLastUser() {
        return lastUser;
    }


    public String getLastUserComment() {
        return lastUserComment;
    }


    public String getLastSent() {
        return lastSent;
    }


    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.lastUser);
        dest.writeString(this.lastUserComment);
        dest.writeString(this.lastSent);
    }


    protected ChatItem(Parcel in) {
        this.id = in.readInt();
        this.lastUser = in.readString();
        this.lastUserComment = in.readString();
        this.lastSent = in.readString();
    }


    public static final Parcelable.Creator<ChatItem> CREATOR =
            new Parcelable.Creator<ChatItem>() {
        @Override
        public ChatItem createFromParcel(Parcel source) {
            return new ChatItem(source);
        }

        @Override
        public ChatItem[] newArray(int size) {
            return new ChatItem[size];
        }
    };
}
