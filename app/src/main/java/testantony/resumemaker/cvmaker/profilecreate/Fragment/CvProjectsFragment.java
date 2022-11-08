package testantony.resumemaker.cvmaker.profilecreate.Fragment;

import android.content.Intent;
import android.view.View;

import testantony.resumemaker.cvmaker.profilecreate.Activity.CVEditActivity;
import testantony.resumemaker.cvmaker.profilecreate.Adapter.CvProjectsAdapter;
import testantony.resumemaker.cvmaker.profilecreate.Adapter.CvResumeEventAdapter;
import testantony.resumemaker.cvmaker.profilecreate.Model.Project;
import testantony.resumemaker.cvmaker.profilecreate.Model.Resume;
import testantony.resumemaker.cvmaker.profilecreate.Template.ResumeEventFragment;
import testantony.resumemaker.cvmaker.profilecreate.Template.ResumeFragment;


public class CvProjectsFragment extends ResumeEventFragment<Project> {
    public static ResumeFragment newInstance(Resume resume) {
        CvProjectsFragment fragmentCvProjects = new CvProjectsFragment();
        fragmentCvProjects.setResume(resume);
        return fragmentCvProjects;
    }


    public void delete(int i) {
        getResume().projects.remove(i);
    }

    public void onClick(int i) {
        Intent projectIntent = CVEditActivity.getProjectIntent(getContext());
        CVEditActivity.setData(projectIntent, i, getResume().projects.get(i));
        startActivityForResult(projectIntent, 2);
    }


    public void addClicked() {
        startActivityForResult(CVEditActivity.getProjectIntent(getContext()), 1);
    }


    public CvResumeEventAdapter<Project> getAdapter(View view) {
        return new CvProjectsAdapter(getResume().projects, this).setEmptyView(view);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 1 && i2 == -1) {
            getResume().projects.add(new Project(CVEditActivity.getEvent(intent)));
            notifyDataChanged();
        }
        if (i == 2 && i2 == -1) {
            getResume().projects.get(intent.getIntExtra(CVEditActivity.FIELD_ID, -1)).cloneThis(CVEditActivity.getEvent(intent));
            notifyDataChanged();
        }
        super.onActivityResult(i, i2, intent);
    }
}
