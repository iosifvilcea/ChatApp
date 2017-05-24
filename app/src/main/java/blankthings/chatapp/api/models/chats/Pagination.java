package blankthings.chatapp.api.models.chats;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by iosif on 5/23/17.
 */

public class Pagination implements Parcelable {

    @SerializedName("current_page")
    private Integer currentPage;

    @SerializedName("per_page")
    private Integer perPage;

    @SerializedName("page_count")
    private Integer pageCount;

    @SerializedName("total_count")
    private Integer totalCount;


    public Pagination() {}

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPerPage() {
        return perPage;
    }

    public void setPerPage(Integer perPage) {
        this.perPage = perPage;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }


    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.currentPage);
        dest.writeValue(this.perPage);
        dest.writeValue(this.pageCount);
        dest.writeValue(this.totalCount);
    }


    protected Pagination(Parcel in) {
        this.currentPage = (Integer) in.readValue(Integer.class.getClassLoader());
        this.perPage = (Integer) in.readValue(Integer.class.getClassLoader());
        this.pageCount = (Integer) in.readValue(Integer.class.getClassLoader());
        this.totalCount = (Integer) in.readValue(Integer.class.getClassLoader());
    }


    public static final Parcelable.Creator<Pagination> CREATOR =
            new Parcelable.Creator<Pagination>() {
        @Override
        public Pagination createFromParcel(Parcel source) {
            return new Pagination(source);
        }

        @Override
        public Pagination[] newArray(int size) {
            return new Pagination[size];
        }
    };
}
