package testantony.resumemaker.cvmaker.profilecreate.Adapter;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import testantony.resumemaker.cvmaker.profilecreate.Model.Skill;
import testantony.resumemaker.cvmaker.profilecreate.Template.Holder;
import testantony.resumemaker.cvmaker.profilecreate.R;

import java.util.ArrayList;

public final class CvSkillAdapter extends RecyclerView.Adapter<Holder> {
    private final ArrayList<Skill> arrayList;

    public CvSkillAdapter(ArrayList<Skill> arrayList2) {
        arrayList = arrayList2;
    }

    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new Holder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_skill, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        Skill skill = arrayList.get(i);
        holder.getTvName$app_release().setText(skill.getName());
        TextView textView = holder.getRateTv$app_release();
        textView.setText(Html.fromHtml(skill.getRatting() + "/5"));
        holder.getRoundedHorizontalProgressBar$app_release().setProgress(skill.getRatting());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
