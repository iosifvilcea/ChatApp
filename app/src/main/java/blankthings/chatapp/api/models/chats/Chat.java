package blankthings.chatapp.api.models.chats;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

import blankthings.chatapp.api.models.ApiError;

/**
 * Created by iosif on 5/22/17.
 */

public class Chat implements Parcelable {

    private List<UserData> data;
    private Meta meta;
    private ApiError errors;


    public Chat() {}


    public List<UserData> getData() {
        return data;
    }

    public Integer getFirstId() {
        return data.get(0).getId();
    }

    public String getFirstName() {
        return data.get(0).getName();
    }

    public String getFirstEmail() {
        return data.get(0).getEmail();
    }


    public void setData(List<UserData> data) {
        this.data = data;
    }


    public Meta getMeta() {
        return meta;
    }


    public void setMeta(Meta meta) {
        this.meta = meta;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.data);
        dest.writeParcelable(this.meta, flags);
        dest.writeParcelable(this.errors, flags);
    }

    protected Chat(Parcel in) {
        this.data = in.createTypedArrayList(UserData.CREATOR);
        this.meta = in.readParcelable(Meta.class.getClassLoader());
        this.errors = in.readParcelable(ApiError.class.getClassLoader());
    }

    public static final Creator<Chat> CREATOR = new Creator<Chat>() {
        @Override
        public Chat createFromParcel(Parcel source) {
            return new Chat(source);
        }

        @Override
        public Chat[] newArray(int size) {
            return new Chat[size];
        }
    };
}
