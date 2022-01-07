package Model;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("name")
    public String name;

    @SerializedName("job")
    public String job;

    public User(String name,String job){
        this.name = name;
        this.job = job;
    }

    public String getName() {
        return name;
    }

    public String getJob() {
        return job;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
