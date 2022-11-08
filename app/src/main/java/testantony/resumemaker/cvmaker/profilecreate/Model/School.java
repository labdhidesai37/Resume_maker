package testantony.resumemaker.cvmaker.profilecreate.Model;

import android.os.Parcel;

public class School extends ResumeEvent {
    public static final Creator<School> CREATOR = new Creator<School>() {
        public School createFromParcel(Parcel parcel) {
            return new School(new ResumeEvent(parcel));
        }

        public School[] newArray(int i) {
            return new School[i];
        }
    };

    public School() {
    }

    public School(ResumeEvent resumeEvent) {
        super(resumeEvent);
    }

    public String getSchoolName() {
        return getTitle();
    }

    public void setSchoolName(String str) {
        setTitle(str);
    }

    public String getLocation() {
        return getDetail();
    }

    public void setLocation(String str) {
        setDetail(str);
    }

    public String getDegree() {
        return getSubtitle();
    }

    public void setDegree(String str) {
        setSubtitle(str);
    }
}
