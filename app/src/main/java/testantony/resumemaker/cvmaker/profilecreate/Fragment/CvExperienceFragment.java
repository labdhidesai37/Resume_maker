package testantony.resumemaker.cvmaker.profilecreate.Fragment;

import android.content.Intent;
import android.view.View;

import testantony.resumemaker.cvmaker.profilecreate.Activity.CVEditActivity;
import testantony.resumemaker.cvmaker.profilecreate.Adapter.CvExperienceAdapter;
import testantony.resumemaker.cvmaker.profilecreate.Adapter.CvResumeEventAdapter;
import testantony.resumemaker.cvmaker.profilecreate.Model.Experience;
import testantony.resumemaker.cvmaker.profilecreate.Model.Resume;
import testantony.resumemaker.cvmaker.profilecreate.Template.ResumeEventFragment;
import testantony.resumemaker.cvmaker.profilecreate.Template.ResumeFragment;

public class CvExperienceFragment extends ResumeEventFragment<Experience> {
    public static ResumeFragment newInstance(Resume resume) {
        CvExperienceFragment fragmentCvExperience = new CvExperienceFragment();
        fragmentCvExperience.setResume(resume);
        return fragmentCvExperience;
    }

    public void delete(int i) {
        getResume().experience.remove(i);
    }

    public void onClick(int i) {
        Intent experienceIntent = CVEditActivity.getExperienceIntent(getContext());
        CVEditActivity.setData(experienceIntent, i, getResume().experience.get(i));
        startActivityForResult(experienceIntent, 2);
    }
    
    public void addClicked() {
        startActivityForResult(CVEditActivity.getExperienceIntent(getContext()), 1);
    }
    
    public CvResumeEventAdapter<Experience> getAdapter(View view) {
        return new CvExperienceAdapter(getResume().experience, this).setEmptyView(view);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 1 && i2 == -1) {
            getResume().experience.add(new Experience(CVEditActivity.getEvent(intent)));
            notifyDataChanged();
        }
        if (i == 2 && i2 == -1) {
            getResume().experience.get(intent.getIntExtra(CVEditActivity.FIELD_ID, -1)).cloneThis(CVEditActivity.getEvent(intent));
            notifyDataChanged();
        }
        super.onActivityResult(i, i2, intent);
    }
}
