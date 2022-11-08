package testantony.resumemaker.cvmaker.profilecreate.Activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.pesonal.adsdk.AppManage;


import testantony.resumemaker.cvmaker.profilecreate.BuildConfig;
import testantony.resumemaker.cvmaker.profilecreate.R;
import testantony.resumemaker.cvmaker.profilecreate.Utils.SharedPrefs;

public final class MainActivity extends AppCompatActivity {
    public boolean doubleBackToExitPressedOnce = false;
    public SharedPrefs prefs;
    public String str;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_main1);

        ((TextView) findViewById(R.id.toolBarTitle)).setText(getResources().getString(R.string.app_name));

        prefs = new SharedPrefs(MainActivity.this);

        AppManage.getInstance(MainActivity.this).loadInterstitialAd(this, AppManage.ADMOB_I[0], AppManage.FACEBOOK_I[0]);


        FrameLayout fl_native = findViewById(R.id.fl_native);
        AppManage.getInstance(MainActivity.this).showNative(fl_native, AppManage.ADMOB_N[0], AppManage.FACEBOOK_N[0]);


        LinearLayout imgshare = findViewById(R.id.imgshare);
        LinearLayout imgrate = findViewById(R.id.imgrate);
       imgshare.setOnClickListener(v -> {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT,
                    "Hey check out my app at: https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID);
            sendIntent.setType("text/plain");
            startActivity(sendIntent);
        });
        imgrate.setOnClickListener(v -> {
            try {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + getPackageName())));
            } catch (ActivityNotFoundException e) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName())));
            }
        });
    }

    @SuppressLint("NonConstantResourceId")
    public final void onButtonClick(View view) {
        String str;
        switch (view.getId()) {
            case R.id.action_education:
                str = "3";
                break;
            case R.id.action_experience:
                str = "4";
                break;
            case R.id.action_hobby:
                AppManage.getInstance(MainActivity.this).showInterstitialAd(MainActivity.this, this::IntentHobby, "", AppManage.app_mainClickCntSwAd);
                return;
            case R.id.action_language:
                AppManage.getInstance(MainActivity.this).showInterstitialAd(MainActivity.this, this::IntentLanguage, "", AppManage.app_mainClickCntSwAd);
                return;
            case R.id.action_personal_info:
                str = "0";
                break;
            case R.id.action_preview:
                str = "5";
                break;
            case R.id.action_projects:
                str = "2";
                break;
            case R.id.otherDetail:
                str = "6";
                break;
            case R.id.userSkill:
                AppManage.getInstance(MainActivity.this).showInterstitialAd(MainActivity.this, this::IntentSkill, "", AppManage.app_mainClickCntSwAd);
                return;
            case R.id.settingDetail:
                AppManage.getInstance(MainActivity.this).showInterstitialAd(MainActivity.this, () -> {
                    IntentResume("7");
                }, "", AppManage.app_mainClickCntSwAd);
                return;
            default:
                str = "1";
                break;
        }

        AppManage.getInstance(MainActivity.this).showInterstitialAd(MainActivity.this, () -> {
            IntentResume(str);
        }, "", AppManage.app_mainClickCntSwAd);
    }

    private void IntentHobby() {
        startActivity(new Intent(this, HobbiesActivity.class));
    }

    private void IntentLanguage() {
        startActivity(new Intent(this, CVLanguageActivity.class));
    }

    private void IntentSkill() {
        startActivity(new Intent(this, SkillActivity.class));
    }

    private void IntentResume(String str) {
        Intent intent = new Intent(this, ResumeActivity.class);
        intent.putExtra("value", str);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        super.onStart();
    }


    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(() -> doubleBackToExitPressedOnce = false, 2000);
    }
}
