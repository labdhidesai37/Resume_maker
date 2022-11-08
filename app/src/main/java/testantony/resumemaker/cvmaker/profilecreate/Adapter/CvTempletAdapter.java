package testantony.resumemaker.cvmaker.profilecreate.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import testantony.resumemaker.cvmaker.profilecreate.Model.ResumeTemp;
import testantony.resumemaker.cvmaker.profilecreate.R;

import java.util.ArrayList;

public class CvTempletAdapter extends RecyclerView.Adapter<CvTempletAdapter.ViewHolder> {
    public Context context;
    public ArrayList<ResumeTemp> list;
    public OnItemSelected mOnItemSelected;

    public interface OnItemSelected {
        void onToolSelected(int i);
    }

    public CvTempletAdapter(OnItemSelected onItemSelected, Context context2, ArrayList<ResumeTemp> arrayList) {
        mOnItemSelected = onItemSelected;
        context = context2;
        list = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_cv_temp_view, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.cvTempView.setImageResource(list.get(i).temp);

        if (list.get(i).isSelected) {
            viewHolder.background.setBackgroundColor(context.getResources().getColor(R.color.teal_200));
        } else {
            viewHolder.background.setBackgroundColor(context.getResources().getColor(R.color.transparent));
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout background;
        ImageView cvTempView;

        ViewHolder(View view) {
            super(view);
            cvTempView = view.findViewById(R.id.cvTempView);
            background = view.findViewById(R.id.background);
            view.setOnClickListener(view1 -> mOnItemSelected.onToolSelected(ViewHolder.this.getLayoutPosition()));
        }
    }
}
