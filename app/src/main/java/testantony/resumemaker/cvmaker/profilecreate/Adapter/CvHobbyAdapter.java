package testantony.resumemaker.cvmaker.profilecreate.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import testantony.resumemaker.cvmaker.profilecreate.Model.Hobby;
import testantony.resumemaker.cvmaker.profilecreate.Template.Holder;
import testantony.resumemaker.cvmaker.profilecreate.R;

import java.util.ArrayList;

public final class CvHobbyAdapter extends RecyclerView.Adapter<Holder> {
    private final ArrayList<Hobby> arrayList;

    public CvHobbyAdapter(ArrayList<Hobby> arrayList2) {
        arrayList = arrayList2;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new Holder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_skill, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(Holder holder, int i) {
        Hobby hobby = arrayList.get(i);
        holder.getRattingLayout$app_release().setVisibility(View.GONE);
        holder.getTvName$app_release().setText(hobby.getName());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
