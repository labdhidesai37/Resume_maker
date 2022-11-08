package testantony.resumemaker.cvmaker.profilecreate.Adapter;

import testantony.resumemaker.cvmaker.profilecreate.Model.School;

import java.util.List;

public class CvSchoolsAdapter extends CvResumeEventAdapter<School> {
    public CvSchoolsAdapter(List<School> list, ResumeEventOnClickListener resumeEventOnClickListener) {
        super(list, resumeEventOnClickListener);
    }
}
