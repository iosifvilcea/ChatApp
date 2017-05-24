package blankthings.chatapp.api.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by iosif on 5/23/17.
 */

public class ApiError implements Parcelable {

    private List<String> errors = null;

    public ApiError() {}

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }


    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringList(this.errors);
    }


    protected ApiError(Parcel in) {
        this.errors = in.createStringArrayList();
    }


    public static final Parcelable.Creator<ApiError> CREATOR = new Parcelable.Creator<ApiError>() {
        @Override
        public ApiError createFromParcel(Parcel source) {
            return new ApiError(source);
        }

        @Override
        public ApiError[] newArray(int size) {
            return new ApiError[size];
        }
    };
}
