package testantony.resumemaker.cvmaker.profilecreate.Adapter;

import testantony.resumemaker.cvmaker.profilecreate.Model.Experience;

import java.util.List;

public class CvExperienceAdapter extends CvResumeEventAdapter<Experience> {
    public CvExperienceAdapter(List<Experience> list, ResumeEventOnClickListener resumeEventOnClickListener) {
        super(list, resumeEventOnClickListener);
    }
}
