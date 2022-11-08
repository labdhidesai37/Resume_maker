package testantony.resumemaker.cvmaker.profilecreate.Model;

import android.os.Parcel;

public class Project extends ResumeEvent {
    public static final Creator<Project> CREATOR = new Creator<Project>() {
        public Project createFromParcel(Parcel parcel) {
            return new Project(new ResumeEvent(parcel));
        }

        public Project[] newArray(int i) {
            return new Project[i];
        }
    };

    public Project() {
    }

    public Project(ResumeEvent resumeEvent) {
        super(resumeEvent);
    }

    public String getName() {
        return getTitle();
    }

    public void setName(String str) {
        setTitle(str);
    }
}
