package blankthings.chatapp.api;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by iosif on 5/22/17.
 */

public class AccountData {

    @SerializedName("data")
    private Data data;

    @SerializedName("meta")
    private Meta meta;

    @SerializedName("errors")
    private ApiError errors;


    public Data getData() {
        return data;
    }


    public void setData(Data data) {
        this.data = data;
    }


    public Meta getMeta() {
        return meta;
    }


    public void setMeta(Meta meta) {
        this.meta = meta;
    }


    public String getName() {
        return data.getName();
    }


    public String getEmail() {
        return data.getEmail();
    }


    public Integer getId() {
        return data.getId();
    }


    public static class Data {
        @SerializedName("id") private Integer id;
        @SerializedName("name") private String name;
        @SerializedName("email") private String email;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }


    public static class ApiError {
        private List<String> errors = null;

        public List<String> getErrors() {
            return errors;
        }

        public void setErrors(List<String> errors) {
            this.errors = errors;
        }
    }


    public static class Meta {
    }
}
