package testantony.resumemaker.cvmaker.profilecreate.Model;

import android.os.Parcel;

public class Experience extends ResumeEvent {
    public static final Creator<Experience> CREATOR = new Creator<Experience>() {
        public Experience createFromParcel(Parcel parcel) {
            return new Experience(new ResumeEvent(parcel));
        }

        public Experience[] newArray(int i) {
            return new Experience[i];
        }
    };

    public Experience() {
    }

    public Experience(ResumeEvent resumeEvent) {
        super(resumeEvent);
    }

    public String getCompany() {
        return getTitle();
    }

    public void setCompany(String str) {
        setTitle(str);
    }

    public String getLocation() {
        return getDetail();
    }

    public void setLocation(String str) {
        setDetail(str);
    }

    public String getJobTitle() {
        return getSubtitle();
    }

    public void setJobTitle(String str) {
        setSubtitle(str);
    }
}
