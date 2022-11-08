package testantony.resumemaker.cvmaker.profilecreate.Activity;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import testantony.resumemaker.cvmaker.profilecreate.Adapter.CvSkillAdapter;

import testantony.resumemaker.cvmaker.profilecreate.Model.Resume;
import testantony.resumemaker.cvmaker.profilecreate.Model.Skill;
import testantony.resumemaker.cvmaker.profilecreate.R;
import testantony.resumemaker.cvmaker.profilecreate.Utils.CustomIORatingBar;
import testantony.resumemaker.cvmaker.profilecreate.Utils.RecyclerHelper;
import testantony.resumemaker.cvmaker.profilecreate.Utils.SharedPrefs;
import testantony.resumemaker.cvmaker.profilecreate.Utils.Utility;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.Gson;
import com.pesonal.adsdk.AppManage;

public class SkillActivity extends AppCompatActivity {
    public int customRatting = 0;
    public SharedPrefs prefs;
    public Resume resume;
    public LinearLayout llAddSkills;
    public CvSkillAdapter recyclerAdapter;
    public RecyclerView recyclerView;
    public RecyclerHelper touchHelper;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setStatusBar();
        setContentView(R.layout.activity_user_skill);

        init();

        touchHelper = new RecyclerHelper(resume.skill, recyclerAdapter);

        touchHelper.setRecyclerItemDragEnabled(true).setOnDragItemListener((i, i2) -> {
        });
        touchHelper.setRecyclerItemSwipeEnabled(true).setOnSwipeItemListener(() -> {
        });

        new ItemTouchHelper(touchHelper).attachToRecyclerView(recyclerView);

        touchHelper.getList();
    }

    @Override
    public void onBackPressed() {
        prefs.setCVData(new Gson().toJson(resume));
        super.onBackPressed();
    }

    public void init() {
        prefs = new SharedPrefs(this);
        recyclerView = findViewById(R.id.recyclerView);
        llAddSkills = findViewById(R.id.llAddSkills);

        ((TextView) findViewById(R.id.toolBarTitle)).setText(getResources().getString(R.string.highlightskill));

        LinearLayout ll_banner = findViewById(R.id.ll_banner);
        AppManage.getInstance(SkillActivity.this).showBanner(ll_banner, AppManage.ADMOB_B[0], AppManage.FACEBOOK_B[0]);

        String cVData = prefs.getCVData();
        Gson gson = new Gson();
        if (cVData.isEmpty()) {
            resume = Resume.createNewResume(this);
        } else {
            resume = gson.fromJson(cVData, Resume.class);
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerAdapter = new CvSkillAdapter(resume.skill);
        recyclerView.setAdapter(recyclerAdapter);

        llAddSkills.setOnClickListener(v -> addNewSkillDialog());

        ImageView ingBack = findViewById(R.id.imgBack);
        ingBack.setOnClickListener(v -> onBackPressed());
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

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == 16908332) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    @SuppressLint({"NotifyDataSetChanged", "ResourceType"})
    private void addNewSkillDialog() {
        customRatting = 0;
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(SkillActivity.this);
        View inflate = getLayoutInflater().inflate(R.layout.bottomsheet_add_skill_hobby_language, null);
        bottomSheetDialog.setContentView(inflate);

        ((TextView) inflate.findViewById(R.id.txtHeading)).setText(Html.fromHtml("Add Skill"));
        final EditText editText = inflate.findViewById(R.id.skillNameEt);
        final TextView textView = inflate.findViewById(R.id.rattingNumber);

        ((CustomIORatingBar) inflate.findViewById(R.id.customIORatingBar)).setRatingChangeListener(i -> {
            customRatting = i;
            textView.setText(i + "/5");
        });

        inflate.findViewById(R.id.addBtn).setOnClickListener(view -> {
            if (editText.getText().toString().length() == 0) {
                Utility.Toast(this, getResources().getString(R.string.enterYourSkillFirst));
            } else if (customRatting == 0) {
                Utility.Toast(this, getResources().getString(R.string.rateYourSkillFirst));
            } else {
                resume.skill.add(0, new Skill(editText.getText().toString(), customRatting));
                recyclerAdapter.notifyDataSetChanged();
                prefs.setCVData(new Gson().toJson(resume));
                bottomSheetDialog.dismiss();
            }
        });

        inflate.findViewById(R.id.btnCancel).setOnClickListener(v -> bottomSheetDialog.dismiss());

        bottomSheetDialog.show();
    }
}
