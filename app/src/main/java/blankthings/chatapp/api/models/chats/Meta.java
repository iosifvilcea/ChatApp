package blankthings.chatapp.api.models.chats;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by iosif on 5/23/17.
 */

public class Meta implements Parcelable {

    @SerializedName("pagination")
    private Pagination pagination;

    public Meta() {
    }

    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.pagination, flags);
    }


    protected Meta(Parcel in) {
        this.pagination = in.readParcelable(Pagination.class.getClassLoader());
    }


    public static final Creator<Meta> CREATOR = new Creator<Meta>() {
        @Override
        public Meta createFromParcel(Parcel source) {
            return new Meta(source);
        }

        @Override
        public Meta[] newArray(int size) {
            return new Meta[size];
        }
    };
}