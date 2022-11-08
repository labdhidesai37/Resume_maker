package testantony.resumemaker.cvmaker.profilecreate.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Skill implements Parcelable {
    public static final Creator<Skill> CREATOR = new Creator<Skill>() {
        public Skill createFromParcel(Parcel parcel) {
            return new Skill(parcel);
        }

        public Skill[] newArray(int i) {
            return new Skill[i];
        }
    };
    private String name;
    private int ratting;

    @Override
    public int describeContents() {
        return 0;
    }

    public Skill() {
        this("Leadership", 5);
    }

    public Skill(String str, int i) {
        this.name = str;
        this.ratting = i;
    }

    protected Skill(Parcel parcel) {
        this.name = parcel.readString();
        this.ratting = parcel.readInt();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.name);
        parcel.writeInt(this.ratting);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public int getRatting() {
        return this.ratting;
    }

    public void setRatting(int i) {
        this.ratting = i;
    }
}
