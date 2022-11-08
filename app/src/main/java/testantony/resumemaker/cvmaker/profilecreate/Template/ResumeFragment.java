package testantony.resumemaker.cvmaker.profilecreate.Template;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import testantony.resumemaker.cvmaker.profilecreate.Model.Resume;

public abstract class ResumeFragment extends Fragment {
    public static final String ARGUMENT_RESUME = "resume";

    public void setResume(Resume resume) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(ARGUMENT_RESUME, resume);
        setArguments(bundle);
    }

    public Resume getResume() {
        return getArguments().getParcelable(ARGUMENT_RESUME);
    }
}
