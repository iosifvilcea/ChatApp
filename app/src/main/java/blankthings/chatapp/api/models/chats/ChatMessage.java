package blankthings.chatapp.api.models.chats;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.google.gson.annotations.SerializedName;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import blankthings.chatapp.utilities.Utils;

/**
 * Created by iosif on 5/23/17.
 */

public class ChatMessage implements Parcelable {

    public static final String KEY = "CHAT_ITEM_KEY";

    private boolean isOutBound;

    @SerializedName("id")
    private Integer id;

    @SerializedName("chat_id")
    private Integer chatId;

    @SerializedName("user_id")
    private Integer userId;

    @SerializedName("message")
    private String message;

    @SerializedName("created_at")
    private String dateCreated;

    @SerializedName("user")
    private User user;

    public ChatMessage() {}

    public Integer getId() {
        return id;
    }

    public Integer getChatId() {
        return chatId;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getMessage() {
        return message;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public String getReadableDate() {
        Date date = Calendar.getInstance().getTime();
        if (dateCreated == null) {
            dateCreated = date.toString();
        }

        try {
            final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            date = df.parse(dateCreated);

        } catch (ParseException e) {
            Log.e(ChatMessage.class.getSimpleName(), e.getMessage());
        }

        return Utils.getFormattedDate(date);
    }

    public User getUser() {
        return user;
    }

    public boolean isOutBound() {
        return (userId == null || userId == 1);
    }

    public void setOutBound(boolean outBound) {
        isOutBound = outBound;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setChatId(Integer chatId) {
        this.chatId = chatId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.isOutBound ? (byte) 1 : (byte) 0);
        dest.writeValue(this.id);
        dest.writeValue(this.chatId);
        dest.writeValue(this.userId);
        dest.writeString(this.message);
        dest.writeString(this.dateCreated);
        dest.writeParcelable(this.user, flags);
    }


    protected ChatMessage(Parcel in) {
        this.isOutBound = in.readByte() != 0;
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.chatId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.userId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.message = in.readString();
        this.dateCreated = in.readString();
        this.user = in.readParcelable(UserData.class.getClassLoader());
    }


    public static final Parcelable.Creator<ChatMessage> CREATOR =
            new Parcelable.Creator<ChatMessage>() {
        @Override
        public ChatMessage createFromParcel(Parcel source) {
            return new ChatMessage(source);
        }

        @Override
        public ChatMessage[] newArray(int size) {
            return new ChatMessage[size];
        }
    };
}