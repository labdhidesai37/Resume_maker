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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import testantony.resumemaker.cvmaker.profilecreate.Adapter.CvHobbyAdapter;

import testantony.resumemaker.cvmaker.profilecreate.Model.Hobby;
import testantony.resumemaker.cvmaker.profilecreate.Model.Resume;
import testantony.resumemaker.cvmaker.profilecreate.R;
import testantony.resumemaker.cvmaker.profilecreate.Utils.RecyclerHelper;
import testantony.resumemaker.cvmaker.profilecreate.Utils.SharedPrefs;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.Gson;
import com.pesonal.adsdk.AppManage;

public class HobbiesActivity extends AppCompatActivity {
    public SharedPrefs prefs;
    public Resume resume;
    public CvHobbyAdapter recyclerAdapter;
    public RecyclerView recyclerView;
    public LinearLayout llAddSkills;
    public RecyclerHelper touchHelper;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setStatusBar();
        setContentView(R.layout.activity_user_skill);

        init();

        touchHelper = new RecyclerHelper(resume.hobbies, recyclerAdapter);

        touchHelper.setRecyclerItemDragEnabled(true).setOnDragItemListener((i, i2) -> {
        });

        touchHelper.setRecyclerItemSwipeEnabled(true).setOnSwipeItemListener(() -> {
        });

        new ItemTouchHelper(touchHelper).attachToRecyclerView(recyclerView);

        touchHelper.getList();
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
    public void onBackPressed() {
        prefs.setCVData(new Gson().toJson(resume));
        super.onBackPressed();
    }

    @SuppressLint("SetTextI18n")
    public void init() {
        prefs = new SharedPrefs(this);
        recyclerView = findViewById(R.id.recyclerView);
        llAddSkills = findViewById(R.id.llAddSkills);

        LinearLayout ll_banner = findViewById(R.id.ll_banner);
        AppManage.getInstance(HobbiesActivity.this).showBanner(ll_banner, AppManage.ADMOB_B[0], AppManage.FACEBOOK_B[0]);

        ((TextView) findViewById(R.id.toolBarTitle)).setText(getResources().getString(R.string.yourHobbies));
        ((TextView) findViewById(R.id.txtName)).setText("Add Hobbies");

        String cVData = prefs.getCVData();
        Gson gson = new Gson();
        if (cVData.isEmpty()) {
            resume = Resume.createNewResume(this);
        } else {
            resume = gson.fromJson(cVData, Resume.class);
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerAdapter = new CvHobbyAdapter(resume.hobbies);
        recyclerView.setAdapter(recyclerAdapter);

        llAddSkills.setOnClickListener(v -> addNewHobbyDialog());

        ImageView ingBack = findViewById(R.id.imgBack);
        ingBack.setOnClickListener(v -> onBackPressed());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == 16908332) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    @SuppressLint({"ResourceType", "NotifyDataSetChanged"})
    private void addNewHobbyDialog() {
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(HobbiesActivity.this);
        View inflate = getLayoutInflater().inflate(R.layout.bottomsheet_add_skill_hobby_language, null);
        bottomSheetDialog.setContentView(inflate);

        ((TextView) inflate.findViewById(R.id.txtHeading)).setText(Html.fromHtml("Add Hobbies"));
        ((TextView) inflate.findViewById(R.id.title)).setText(getResources().getString(R.string.enterYourHobby));
        final EditText editText = inflate.findViewById(R.id.skillNameEt);
        inflate.findViewById(R.id.rattingLayout).setVisibility(View.GONE);

        inflate.findViewById(R.id.addBtn).setOnClickListener(view -> {
            if (editText.getText().toString().length() == 0) {
                Toast.makeText(HobbiesActivity.this, getResources().getString(R.string.enterYourHobbyFirst), Toast.LENGTH_SHORT).show();
                return;
            }
            resume.hobbies.add(0, new Hobby(editText.getText().toString()));
            recyclerAdapter.notifyDataSetChanged();
            prefs.setCVData(new Gson().toJson(resume));
            bottomSheetDialog.dismiss();
        });

        inflate.findViewById(R.id.btnCancel).setOnClickListener(view -> bottomSheetDialog.dismiss());

        bottomSheetDialog.show();
    }
}
