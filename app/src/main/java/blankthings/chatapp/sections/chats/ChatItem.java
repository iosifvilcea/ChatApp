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
    private String date;
    private boolean isOutBound;


    public ChatItem(int id, String user, String message, String date, boolean isOutBound) {
        this.id = id;
        this.user = user;
        this.message = message;
        this.date = date;
        this.isOutBound = isOutBound;
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


    public String getDate() {
        return date;
    }


    public boolean isOutBound() {
        return isOutBound;
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
        dest.writeString(this.date);
        dest.writeByte(this.isOutBound ? (byte) 1 : (byte) 0);
    }


    protected ChatItem(Parcel in) {
        this.id = in.readInt();
        this.user = in.readString();
        this.message = in.readString();
        this.date = in.readString();
        this.isOutBound = in.readByte() != 0;
    }


    public static final Creator<ChatItem> CREATOR = new Creator<ChatItem>() {
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
