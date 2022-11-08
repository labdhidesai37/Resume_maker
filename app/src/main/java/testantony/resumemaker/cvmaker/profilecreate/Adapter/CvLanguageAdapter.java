package testantony.resumemaker.cvmaker.profilecreate.Adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import testantony.resumemaker.cvmaker.profilecreate.Model.Language;
import testantony.resumemaker.cvmaker.profilecreate.Template.Holder;
import testantony.resumemaker.cvmaker.profilecreate.R;

import java.util.ArrayList;


public final class CvLanguageAdapter extends RecyclerView.Adapter<Holder> {
    private final ArrayList<Language> arrayList;

    public CvLanguageAdapter(ArrayList<Language> arrayList2) {
        arrayList = arrayList2;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new Holder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_skill, viewGroup, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(Holder holder, int i) {
        Language language = arrayList.get(i);
        holder.getTvName$app_release().setText(language.getName());
        TextView textView = holder.getRateTv$app_release();
        textView.setText(language.getRatting() + "/5");
        holder.getRoundedHorizontalProgressBar$app_release().setProgress(language.getRatting());
    }

    public int getItemCount() {
        return arrayList.size();
    }
}
