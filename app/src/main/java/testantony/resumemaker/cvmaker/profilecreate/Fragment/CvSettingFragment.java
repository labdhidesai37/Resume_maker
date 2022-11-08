package testantony.resumemaker.cvmaker.profilecreate.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;

import testantony.resumemaker.cvmaker.profilecreate.Model.Resume;
import testantony.resumemaker.cvmaker.profilecreate.Model.Setting;
import testantony.resumemaker.cvmaker.profilecreate.Template.ResumeFragment;
import testantony.resumemaker.cvmaker.profilecreate.Template.TextChangeListener;
import testantony.resumemaker.cvmaker.profilecreate.R;
import testantony.resumemaker.cvmaker.profilecreate.Utils.Utility;

public class CvSettingFragment extends ResumeFragment {
    public Resume resume;
    public EditText aboutEt;
    public EditText achievementET;
    public EditText contactEt;
    public EditText educationEt;
    public EditText experienceEt;
    public EditText hobbyEt;
    public EditText languageEt;
    public EditText projectET;
    public EditText referenceEt;
    public EditText skillET;
    public EditText socialLinkEt;

    public static ResumeFragment newInstance(Resume resume2) {
        CvSettingFragment fragmentCvSetting = new CvSettingFragment();
        fragmentCvSetting.setResume(resume2);
        return fragmentCvSetting;
    }

    @Override
    public void onCreate(Bundle bundle) {
        setHasOptionsMenu(true);
        super.onCreate(bundle);
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_setting, viewGroup, false);

        resume = getResume();

        aboutEt = inflate.findViewById(R.id.aboutEt);
        contactEt = inflate.findViewById(R.id.contactEt);
        educationEt = inflate.findViewById(R.id.educationEt);
        experienceEt = inflate.findViewById(R.id.experienceEt);
        skillET = inflate.findViewById(R.id.skillET);
        projectET = inflate.findViewById(R.id.projectET);
        socialLinkEt = inflate.findViewById(R.id.socialLinkEt);
        referenceEt = inflate.findViewById(R.id.referenceEt);
        achievementET = inflate.findViewById(R.id.achievementET);
        projectET = inflate.findViewById(R.id.projectET);
        languageEt = inflate.findViewById(R.id.languageEt);
        hobbyEt = inflate.findViewById(R.id.hobbyEt);

        setText();

        aboutEt.addTextChangedListener(new TextChangeListener() {
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
             //   resume.setting.setAbout(charSequence.toString());
            }
        });

        contactEt.addTextChangedListener(new TextChangeListener() {
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                resume.setting.setContact(charSequence.toString());
            }
        });

        educationEt.addTextChangedListener(new TextChangeListener() {
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                resume.setting.setEducation(charSequence.toString());
            }
        });

        experienceEt.addTextChangedListener(new TextChangeListener() {
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                resume.setting.setExperience(charSequence.toString());
            }
        });

        skillET.addTextChangedListener(new TextChangeListener() {
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                resume.setting.setSkill(charSequence.toString());
            }
        });

        projectET.addTextChangedListener(new TextChangeListener() {
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                resume.setting.setProject(charSequence.toString());
            }
        });

        hobbyEt.addTextChangedListener(new TextChangeListener() {
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                resume.setting.setHobby(charSequence.toString());
            }
        });

        languageEt.addTextChangedListener(new TextChangeListener() {
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                resume.setting.setLanguage(charSequence.toString());
            }
        });

        achievementET.addTextChangedListener(new TextChangeListener() {
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                resume.setting.setAchievements(charSequence.toString());
            }
        });

        referenceEt.addTextChangedListener(new TextChangeListener() {
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                resume.setting.setReference(charSequence.toString());
            }
        });

        socialLinkEt.addTextChangedListener(new TextChangeListener() {
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                resume.setting.setSocialLink(charSequence.toString());
            }
        });
        return inflate;
    }

    private void setText() {
        languageEt.setText(resume.setting.getLanguage());
        achievementET.setText(resume.setting.getAchievements());
        referenceEt.setText(resume.setting.getReference());
        aboutEt.setText(resume.setting.getAbout());
        contactEt.setText(resume.setting.getContact());
        educationEt.setText(resume.setting.getEducation());
        experienceEt.setText(resume.setting.getExperience());
        skillET.setText(resume.setting.getSkill());
        projectET.setText(resume.setting.getProject());
        hobbyEt.setText(resume.setting.getHobby());
        socialLinkEt.setText(resume.setting.getSocialLink());
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
        requireActivity().getMenuInflater().inflate(R.menu.reset, menu);
        super.onCreateOptionsMenu(menu, menuInflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.action_reset) {
            resume.setting = new Setting(requireActivity());
            setText();
            Utility.Toast(getActivity(), requireActivity().getResources().getString(R.string.resumeHiglightRestSuccessfully));
        }
        return super.onOptionsItemSelected(menuItem);
    }
}
