package blankthings.chatapp.api.models.account;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import blankthings.chatapp.api.models.ApiError;
import blankthings.chatapp.api.models.chats.Meta;
import blankthings.chatapp.api.models.chats.User;

/**
 * Created by iosif on 5/23/17.
 */

public class Account implements Parcelable {

    @SerializedName("data")
    private User user;

    private Meta meta;

    private ApiError errors;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public ApiError getErrors() {
        return errors;
    }

    public void setErrors(ApiError errors) {
        this.errors = errors;
    }


    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.user,flags);
        dest.writeParcelable(this.meta, flags);
        dest.writeParcelable(this.errors, flags);
    }


    protected Account(Parcel in) {
        this.user = in.readParcelable(User.class.getClassLoader());
        this.meta = in.readParcelable(Meta.class.getClassLoader());
        this.errors = in.readParcelable(ApiError.class.getClassLoader());
    }


    public static final Parcelable.Creator<Account> CREATOR = new Parcelable.Creator<Account>() {
        @Override
        public Account createFromParcel(Parcel source) {
            return new Account(source);
        }

        @Override
        public Account[] newArray(int size) {
            return new Account[size];
        }
    };
}
