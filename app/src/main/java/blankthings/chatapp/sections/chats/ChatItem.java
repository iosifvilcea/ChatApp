package blankthings.chatapp.sections.chats;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by iosif on 5/20/17.
 */

public class ChatItem implements Parcelable {

    public static final String KEY = "CHAT_ITEM_KEY";

    private int id;
    private String user;
    private String message;
    private String dateReceived;

    public ChatItem(int id, String user, String message, String dateReceived) {
        this.id = id;
        this.user = user;
        this.message = message;
        this.dateReceived = dateReceived;
    }


    public int getId() {
        return id;
    }


    public String getUser() {
        return user;
    }


    public String getMessage() {
        return message;
    }


    public String getDateReceived() {
        return dateReceived;
    }


    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.user);
        dest.writeString(this.message);
        dest.writeString(this.dateReceived);
    }


    protected ChatItem(Parcel in) {
        this.id = in.readInt();
        this.user = in.readString();
        this.message = in.readString();
        this.dateReceived = in.readString();
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
