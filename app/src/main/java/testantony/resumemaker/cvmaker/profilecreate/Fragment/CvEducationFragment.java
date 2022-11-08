package testantony.resumemaker.cvmaker.profilecreate.Fragment;

import android.content.Intent;
import android.view.View;

import testantony.resumemaker.cvmaker.profilecreate.Activity.CVEditActivity;
import testantony.resumemaker.cvmaker.profilecreate.Adapter.CvResumeEventAdapter;
import testantony.resumemaker.cvmaker.profilecreate.Adapter.CvSchoolsAdapter;
import testantony.resumemaker.cvmaker.profilecreate.Model.Resume;
import testantony.resumemaker.cvmaker.profilecreate.Model.School;
import testantony.resumemaker.cvmaker.profilecreate.Template.ResumeEventFragment;
import testantony.resumemaker.cvmaker.profilecreate.Template.ResumeFragment;


public class CvEducationFragment extends ResumeEventFragment<School> {

    public static ResumeFragment newInstance(Resume resume) {
        CvEducationFragment fragmentCvEducation = new CvEducationFragment();
        fragmentCvEducation.setResume(resume);
        return fragmentCvEducation;
    }

    public void delete(int i) {
        getResume().schools.remove(i);
    }

    public void onClick(int i) {
        Intent schoolIntent = CVEditActivity.getSchoolIntent(getContext());
        CVEditActivity.setData(schoolIntent, i, getResume().schools.get(i));
        startActivityForResult(schoolIntent, 2);
    }

    public void addClicked() {
        startActivityForResult(CVEditActivity.getSchoolIntent(getContext()), 1);
    }

    public CvResumeEventAdapter<School> getAdapter(View view) {
        return new CvSchoolsAdapter(getResume().schools, this).setEmptyView(view);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 1 && i2 == -1) {
            getResume().schools.add(new School(CVEditActivity.getEvent(intent)));
            notifyDataChanged();
        }
        if (i == 2 && i2 == -1) {
            getResume().schools.get(intent.getIntExtra(CVEditActivity.FIELD_ID, -1)).cloneThis(CVEditActivity.getEvent(intent));
            notifyDataChanged();
        }
        super.onActivityResult(i, i2, intent);
    }


}
