package blankthings.chatapp.sections.profile;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by iosif on 5/20/17.
 */

public class Profile implements Parcelable {

    private int id;
    private String name;
    private String email;
    private String pass;


    private Profile() {}


    public Profile(int id, String name, String email, String pass) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.pass = pass;
    }


    public Profile(Profile profile) {
        id = profile.getId();
        name = profile.getName();
        email = profile.getEmail();
        pass = profile.getPass();
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


    public String getPass() {
        return pass;
    }


    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.email);
        dest.writeString(this.pass);
    }


    protected Profile(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.email = in.readString();
        this.pass = in.readString();
    }


    public static final Parcelable.Creator<Profile> CREATOR =
            new Parcelable.Creator<Profile>() {
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
