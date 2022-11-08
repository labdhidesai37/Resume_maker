package testantony.resumemaker.cvmaker.profilecreate.Activity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;


import testantony.resumemaker.cvmaker.profilecreate.Fragment.CvEducationFragment;
import testantony.resumemaker.cvmaker.profilecreate.Fragment.CvExperienceFragment;
import testantony.resumemaker.cvmaker.profilecreate.Fragment.CvOtherDetailFragment;
import testantony.resumemaker.cvmaker.profilecreate.Fragment.CvPersonalInfoFragment;
import testantony.resumemaker.cvmaker.profilecreate.Fragment.CvPreviewFragment;
import testantony.resumemaker.cvmaker.profilecreate.Fragment.CvProjectsFragment;
import testantony.resumemaker.cvmaker.profilecreate.Fragment.CvSettingFragment;
import testantony.resumemaker.cvmaker.profilecreate.Model.Resume;
import testantony.resumemaker.cvmaker.profilecreate.R;
import testantony.resumemaker.cvmaker.profilecreate.Template.ResumeFragment;
import testantony.resumemaker.cvmaker.profilecreate.Utils.SharedPrefs;
import com.google.gson.Gson;
import com.pesonal.adsdk.AppManage;

public class ResumeActivity extends AppCompatActivity {
    public SharedPrefs mPrefs;
    public Resume resume;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setStatusBar();
        setContentView(R.layout.activity_resume);

        mPrefs = new SharedPrefs(this);

        LinearLayout ll_banner = findViewById(R.id.ll_banner);
        AppManage.getInstance(ResumeActivity.this).showBanner(ll_banner, AppManage.ADMOB_B[0], AppManage.FACEBOOK_B[0]);

        Gson gson = new Gson();
        String cVData = mPrefs.getCVData();
        if (cVData.isEmpty()) {
            resume = Resume.createNewResume(this);
        } else {
            resume = gson.fromJson(cVData, Resume.class);
        }

        if (getIntent() != null) {
            setFragment(getIntent().getStringExtra("value"));
        }

        ImageView imgBack = findViewById(R.id.imgBack);
        imgBack.setOnClickListener(v -> onBackPressed());
    }

    @Override
    public void onStop() {
        super.onStop();
        mPrefs.setCVData(new Gson().toJson(resume));
    }

    private void setFragment(String str) {
        String str2;
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case 48:
                if (str.equals("0")) {
                    c = 0;
                    break;
                }
                break;
            case 49:
                if (str.equals("1")) {
                    c = 1;
                    break;
                }
                break;
            case 50:
                if (str.equals("2")) {
                    c = 2;
                    break;
                }
                break;
            case 51:
                if (str.equals("3")) {
                    c = 3;
                    break;
                }
                break;
            case 52:
                if (str.equals("4")) {
                    c = 4;
                    break;
                }
                break;
            case 53:
                if (str.equals("5")) {
                    c = 5;
                    break;
                }
                break;
            case 54:
                if (str.equals("6")) {
                    c = 6;
                    break;
                }
                break;
            case 55:
                if (str.equals("7")) {
                    c = 7;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                openFragment(CvPersonalInfoFragment.newInstance(resume));
                str2 = getResources().getString(R.string.personalInfo);
                break;
            case 1:
            case 6:
                openFragment(CvOtherDetailFragment.newInstance(resume));
                str2 = getResources().getString(R.string.otherDetail);
                break;
            case 2:
                openFragment(CvProjectsFragment.newInstance(resume));
                str2 = getResources().getString(R.string.project);
                break;
            case 3:
                openFragment(CvEducationFragment.newInstance(resume));
                str2 = getResources().getString(R.string.education);
                break;
            case 4:
                openFragment(CvExperienceFragment.newInstance(resume));
                str2 = getResources().getString(R.string.experience);
                break;
            case 5:
                openFragment(CvPreviewFragment.newInstance(resume));
                str2 = getResources().getString(R.string.preview);
                break;
            case 7:
                openFragment(CvSettingFragment.newInstance(resume));
                str2 = getResources().getString(R.string.setting);
                break;
            default:
                str2 = "";
                break;
        }
        ((TextView) findViewById(R.id.toolBarTitle)).setText(str2);
    }

    private void openFragment(ResumeFragment resumeFragment) {
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        beginTransaction.replace(R.id.content_frame, resumeFragment);
        beginTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void setStatusBar() {
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= 23) {
            window.getDecorView().setSystemUiVisibility(window.getDecorView().getSystemUiVisibility() | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.bg_color));
            window.setNavigationBarColor(ContextCompat.getColor(this, R.color.bg_color));
        } else if (Build.VERSION.SDK_INT == 21 || Build.VERSION.SDK_INT == 22) {
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.bg_color));
            window.setNavigationBarColor(ContextCompat.getColor(this, R.color.bg_color));
        } else {
            window.clearFlags(0);
        }
    }
}
