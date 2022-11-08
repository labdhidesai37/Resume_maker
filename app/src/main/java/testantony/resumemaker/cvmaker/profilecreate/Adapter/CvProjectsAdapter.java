package testantony.resumemaker.cvmaker.profilecreate.Adapter;

import android.view.View;

import testantony.resumemaker.cvmaker.profilecreate.Model.Project;

import java.util.List;

public class CvProjectsAdapter extends CvResumeEventAdapter<Project> {
    public CvProjectsAdapter(List<Project> list, ResumeEventOnClickListener resumeEventOnClickListener) {
        super(list, resumeEventOnClickListener);
    }

    public void updateViewHolder(CvResumeEventAdapterViewHolder cvResumeEventAdapterViewHolder) {
        cvResumeEventAdapterViewHolder.subtitle.setVisibility(View.GONE);
    }
}
