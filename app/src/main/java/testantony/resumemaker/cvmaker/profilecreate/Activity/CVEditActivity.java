package testantony.resumemaker.cvmaker.profilecreate.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
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

import com.pesonal.adsdk.AppManage;


import testantony.resumemaker.cvmaker.profilecreate.Model.ResumeEvent;
import testantony.resumemaker.cvmaker.profilecreate.R;
import testantony.resumemaker.cvmaker.profilecreate.Template.TextChangeListener;

public class CVEditActivity extends AppCompatActivity {
    public static final String FIELD_DETAIL = "details";
    public static final String FIELD_FROM_DATE = "fromDate";
    public static final String FIELD_ID = "id";
    public static final String FIELD_SUBTITLE = "subtitle";
    public static final String FIELD_TO_DATE = "toDate";
    public static final String TYPE_EXPERIENCE = "experience";
    public static final String TYPE_PROJECT = "project";
    public static final String TYPE_SCHOOL = "school";

    boolean subtitleEnabled = true;
    public int f1052id = -1;
    public String description = "";
    public EditText descriptionEditText;
    public String detail = "";
    public EditText detailEditText;
    public String fromDate = "";
    public EditText fromDateEt;
    public String subtitle = "";
    public EditText subtitleEditText;
    public String title = "";
    public EditText titleEditText;
    public String toDate = "";
    public EditText toDateEt;

    public static Intent setData(Intent intent, int i, ResumeEvent resumeEvent) {
        intent.putExtra(FIELD_ID, i);
        intent.putExtra("title", resumeEvent.getTitle());
        intent.putExtra(FIELD_DETAIL, resumeEvent.getDetail());
        intent.putExtra(FIELD_SUBTITLE, resumeEvent.getSubtitle());
        intent.putExtra("description", resumeEvent.getDescription());
        intent.putExtra(FIELD_FROM_DATE, resumeEvent.getFromDate());
        intent.putExtra(FIELD_TO_DATE, resumeEvent.getToDate());
        return intent;
    }

    public static Intent getProjectIntent(Context context) {
        Intent intent = new Intent(context, CVEditActivity.class);
        intent.putExtra("type", TYPE_PROJECT);
        return intent;
    }

    public static Intent getSchoolIntent(Context context) {
        Intent intent = new Intent(context, CVEditActivity.class);
        intent.putExtra("type", TYPE_SCHOOL);
        return intent;
    }

    public static Intent getExperienceIntent(Context context) {
        Intent intent = new Intent(context, CVEditActivity.class);
        intent.putExtra("type", TYPE_EXPERIENCE);
        return intent;
    }

    public static ResumeEvent getEvent(Intent intent) {
        return new ResumeEvent(intent.getStringExtra("title"), intent.getStringExtra(FIELD_DETAIL), intent.getStringExtra(FIELD_SUBTITLE), intent.getStringExtra("description"), intent.getStringExtra(FIELD_FROM_DATE), intent.getStringExtra(FIELD_TO_DATE));
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setStatusBar();
        char c;
        String str;
        Intent intent = getIntent();
        String stringExtra = intent.getStringExtra("type");
        stringExtra.hashCode();
        switch (stringExtra.hashCode()) {
            case -907977868:
                if (stringExtra.equals(TYPE_SCHOOL)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -309310695:
                if (stringExtra.equals(TYPE_PROJECT)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -85567126:
                if (stringExtra.equals(TYPE_EXPERIENCE)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                setContentView(R.layout.activity_edit_school);
                str = getResources().getString(R.string.school);
                break;
            case 1:
                setContentView(R.layout.activity_edit_project);
                subtitleEnabled = false;
                str = getResources().getString(R.string.project);
                break;
            case 2:
                setContentView(R.layout.activity_edit_experience);
                str = getResources().getString(R.string.experience);
                break;
            default:
                str = "";
                break;
        }

        ((TextView) findViewById(R.id.toolBarTitle)).setText(str);

        titleEditText = findViewById(R.id.input_title);
        detailEditText = findViewById(R.id.input_detail);
        subtitleEditText = findViewById(R.id.input_subtitle);
        descriptionEditText = findViewById(R.id.input_description);
        fromDateEt = findViewById(R.id.fromDateEt);
        toDateEt = findViewById(R.id.toDateEt);

        LinearLayout ll_banner = findViewById(R.id.ll_banner);
        AppManage.getInstance(CVEditActivity.this).showBanner(ll_banner, AppManage.ADMOB_B[0], AppManage.FACEBOOK_B[0]);

        ImageView imgBack = findViewById(R.id.imgBack);
        imgBack.setOnClickListener(v -> onBackPressed());

        titleEditText.addTextChangedListener(new TextChangeListener() {
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                title = charSequence.toString();
            }
        });

        detailEditText.addTextChangedListener(new TextChangeListener() {
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                detail = charSequence.toString();
            }
        });

        subtitleEditText.addTextChangedListener(new TextChangeListener() {
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                subtitle = charSequence.toString();
            }
        });

        descriptionEditText.addTextChangedListener(new TextChangeListener() {
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                description = charSequence.toString();
            }
        });

        fromDateEt.addTextChangedListener(new TextChangeListener() {
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                fromDate = charSequence.toString();
            }
        });

        toDateEt.addTextChangedListener(new TextChangeListener() {
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                toDate = charSequence.toString();
            }
        });

        if (intent.hasExtra(FIELD_ID)) {
            f1052id = intent.getIntExtra(FIELD_ID, -1);
            title = intent.getStringExtra("title");
            detail = intent.getStringExtra(FIELD_DETAIL);
            subtitle = intent.getStringExtra(FIELD_SUBTITLE);
            description = intent.getStringExtra("description");
            fromDate = intent.getStringExtra(FIELD_FROM_DATE);
            toDate = intent.getStringExtra(FIELD_TO_DATE);
            titleEditText.setText(title);
            detailEditText.setText(detail);
            subtitleEditText.setText(subtitle);
            descriptionEditText.setText(description);
            fromDateEt.setText(fromDate);
            toDateEt.setText(toDate);
        }
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.save, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != R.id.action_save) {
            if (menuItem.getItemId() == 16908332) {
                onBackPressed();
            }
            return super.onOptionsItemSelected(menuItem);
        } else if (validInput()) {
            setResult(-1, getResultIntent());
            finish();
            return true;
        } else {
            Toast.makeText(this, getResources().getString(R.string.allFieldAreRequired), Toast.LENGTH_LONG).show();
            return false;
        }
    }

    private boolean validInput() {
        boolean z = !TextUtils.isEmpty(titleEditText.getText());
        if (subtitleEnabled && TextUtils.isEmpty(detailEditText.getText())) {
            z = false;
        }
        if (TextUtils.isEmpty(descriptionEditText.getText())) {
            z = false;
        }
        if (subtitleEnabled && TextUtils.isEmpty(subtitleEditText.getText())) {
            z = false;
        }
        if (TextUtils.isEmpty(fromDateEt.getText())) {
            z = false;
        }
        if (TextUtils.isEmpty(toDateEt.getText())) {
            return false;
        }
        return z;
    }

    private Intent getResultIntent() {
        Intent intent = new Intent();
        int i = f1052id;
        if (i != -1) {
            intent.putExtra(FIELD_ID, i);
        }
        intent.putExtra("title", title);
        intent.putExtra(FIELD_DETAIL, detail);
        intent.putExtra(FIELD_SUBTITLE, subtitle);
        intent.putExtra("description", description);
        intent.putExtra(FIELD_FROM_DATE, fromDate);
        intent.putExtra(FIELD_TO_DATE, toDate);
        return intent;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
