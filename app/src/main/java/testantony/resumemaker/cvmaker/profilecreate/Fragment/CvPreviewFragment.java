package testantony.resumemaker.cvmaker.profilecreate.Fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintJob;
import android.print.PrintManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import testantony.resumemaker.cvmaker.profilecreate.Activity.CVEditActivity;
import testantony.resumemaker.cvmaker.profilecreate.Adapter.CvTempletAdapter;
import testantony.resumemaker.cvmaker.profilecreate.Model.Resume;
import testantony.resumemaker.cvmaker.profilecreate.Model.ResumeTemp;
import testantony.resumemaker.cvmaker.profilecreate.Template.ResumeFragment;
import testantony.resumemaker.cvmaker.profilecreate.Template.ResumeTemplate1;
import testantony.resumemaker.cvmaker.profilecreate.Template.ResumeTemplate2;
import testantony.resumemaker.cvmaker.profilecreate.Template.ResumeTemplate3;
import testantony.resumemaker.cvmaker.profilecreate.Template.ResumeTemplate4;
import testantony.resumemaker.cvmaker.profilecreate.Template.ResumeTemplate5;
import testantony.resumemaker.cvmaker.profilecreate.Template.ResumeTemplate6;
import testantony.resumemaker.cvmaker.profilecreate.Template.ResumeTemplate7;
import testantony.resumemaker.cvmaker.profilecreate.R;
import testantony.resumemaker.cvmaker.profilecreate.Utils.SharedPrefs;
import testantony.resumemaker.cvmaker.profilecreate.Utils.Utility;

import java.util.ArrayList;

public class CvPreviewFragment extends ResumeFragment {
    public boolean isPaidTemplate = false;
    public ArrayList<ResumeTemp> list;
    public RecyclerView rvFilterView;
    public CvTempletAdapter mCvTempletAdapter;
    public PrintDocumentAdapter printAdapter;
    public SharedPrefs prefs;
    public PrintJob printJob;
    public Resume resume;
    public WebView webView;

    public static ResumeFragment newInstance(Resume resume2) {
        CvPreviewFragment fragmentCvPreview = new CvPreviewFragment();
        fragmentCvPreview.setResume(resume2);
        return fragmentCvPreview;
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setHasOptionsMenu(true);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_preview, viewGroup, false);

        prefs = new SharedPrefs(requireActivity());
        resume = getResume();

        rvFilterView = inflate.findViewById(R.id.rvFilterView);
        webView = inflate.findViewById(R.id.webView);

        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setAllowFileAccess(true);
        webView.setInitialScale(1);

        list = new ArrayList<>();
        list.add(new ResumeTemp(R.drawable.resume_3, true, false));
        list.add(new ResumeTemp(R.drawable.resume_3, false, false));
        list.add(new ResumeTemp(R.drawable.resume_3, false, false));
        list.add(new ResumeTemp(R.drawable.resume_3, false, false));
        list.add(new ResumeTemp(R.drawable.resume_3, false, false));
        list.add(new ResumeTemp(R.drawable.resume_3, false, false));
        list.add(new ResumeTemp(R.drawable.resume_3, false, false));

        webView.loadDataWithBaseURL(null, new ResumeTemplate7().getContent(getActivity(), resume), "text/html", "utf-8", null);

        mCvTempletAdapter = new CvTempletAdapter(i -> {
            String content;
            for (int i2 = 0; i2 < list.size(); i2++) {
                list.get(i2).setSelected(false);
            }
            list.get(i).setSelected(true);
            mCvTempletAdapter.notifyDataSetChanged();
            isPaidTemplate = false;
            if (i == 0) {
                content = new ResumeTemplate1().getContent(getActivity(), resume);
            } else if (i == 1) {
                content = new ResumeTemplate2().getContent(getActivity(), resume);
            } else if (i == 2) {
                content = new ResumeTemplate3().getContent(getActivity(), resume);
            } else if (i == 3) {
                content = new ResumeTemplate4().getContent(getActivity(), resume);
            } else if (i == 4) {
                content = new ResumeTemplate5().getContent(getActivity(), resume);
            } else if (i == 5) {
                content = new ResumeTemplate6().getContent(getActivity(), resume);
            } else {
                content = new ResumeTemplate7().getContent(getActivity(), resume);
            }
            webView.loadDataWithBaseURL(null, content, "text/html", "utf-8", null);
        }, getActivity(), list);

        rvFilterView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        rvFilterView.setAdapter(mCvTempletAdapter);
        return inflate;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.print, menu);
        super.onCreateOptionsMenu(menu, menuInflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.action_print) {
            if (prefs.isOpenPdfSaveDialog()) {
                howToSaveInfoDialog();
            } else {
                createWebPrintJob(webView);
            }
        }
        return super.onOptionsItemSelected(menuItem);
    }


    @SuppressLint({"ObsoleteSdkInt", "WrongConstant"})
    public void createWebPrintJob(WebView webView2) {
        PrintManager printManager = (PrintManager) requireActivity().getSystemService(Context.PRINT_SERVICE);
        if (Build.VERSION.SDK_INT >= 21) {
            printAdapter = webView2.createPrintDocumentAdapter("myCV.pdf");
        } else {
            printAdapter = webView2.createPrintDocumentAdapter();
        }
        printJob = printManager.print(getString(R.string.app_name) + " Document", printAdapter, new PrintAttributes.Builder().setMediaSize(PrintAttributes.MediaSize.ISO_A4).setResolution(new PrintAttributes.Resolution(CVEditActivity.FIELD_ID, "print", 200, 200)).setColorMode(2).setMinMargins(PrintAttributes.Margins.NO_MARGINS).build());
    }

    private void howToSaveInfoDialog() {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(requireActivity());
        View inflate = getLayoutInflater().inflate(R.layout.bottomsheet_save_cv, null);
        bottomSheetDialog.setContentView(inflate);

        inflate.findViewById(R.id.gotItBtn).setOnClickListener(view -> {
            createWebPrintJob(webView);
            bottomSheetDialog.dismiss();
        });

        inflate.findViewById(R.id.btnCancel).setOnClickListener(view -> {
            createWebPrintJob(webView);
            bottomSheetDialog.dismiss();
        });

        ((CheckBox) inflate.findViewById(R.id.neverShowAgainCB)).setOnCheckedChangeListener((compoundButton, z) -> {
            createWebPrintJob(webView);
            prefs.setOpenPdfSaveDialog(z);
            bottomSheetDialog.dismiss();
        });

        bottomSheetDialog.show();
    }

    public void onResume() {
        super.onResume();
        if (printJob != null && printJob.isCompleted()) {
            Utility.Toast(getActivity(), getResources().getString(R.string.resumeCreateSuccessfullyAndCanBeFoundMessage));
        }
    }
}
