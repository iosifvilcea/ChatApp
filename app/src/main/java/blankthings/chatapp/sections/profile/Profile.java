package blankthings.chatapp.sections.profile;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by iosif on 5/20/17.
 */

public class Profile implements Parcelable {

    public static final String KEY = "KEY";

    private int id;
    private String name;
    private String email;

    /** Stored here for convenience */
    private String auth;

    private Profile() {}


    public Profile(int id, String name, String email, String auth) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.auth = auth;
    }


    public Profile(Profile profile) {
        id = profile.getId();
        name = profile.getName();
        email = profile.getEmail();
        auth =profile.getAuth();
    }



    public int getId() {
        return id;
    }


    public String getName() {
        return name;
    }


    public String getEmail() {
        return email;
    }


    public String getAuth() {
        return auth;
    }


    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.auth);
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.email);
    }


    protected Profile(Parcel in) {
        this.auth = in.readString();
        this.id = in.readInt();
        this.name = in.readString();
        this.email = in.readString();
    }


    public static final Creator<Profile> CREATOR = new Creator<Profile>() {
        @Override
        public Profile createFromParcel(Parcel source) {
            return new Profile(source);
        }

        @Override
        public Profile[] newArray(int size) {
            return new Profile[size];
        }
    };
}
