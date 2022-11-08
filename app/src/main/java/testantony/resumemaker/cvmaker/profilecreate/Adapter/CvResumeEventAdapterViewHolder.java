package testantony.resumemaker.cvmaker.profilecreate.Adapter;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import testantony.resumemaker.cvmaker.profilecreate.R;

public class CvResumeEventAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    CvResumeEventAdapter.ResumeEventOnClickListener mResumeEventOnClickListener;
    TextView description;
    TextView detail;
    TextView fromToDate;
    TextView subtitle;
    TextView title;

    public CvResumeEventAdapterViewHolder(View view, CvResumeEventAdapter.ResumeEventOnClickListener resumeEventOnClickListener) {
        super(view);
        title = view.findViewById(R.id.title);
        detail = view.findViewById(R.id.detail);
        subtitle = view.findViewById(R.id.subtitle);
        description = view.findViewById(R.id.description);
        fromToDate = view.findViewById(R.id.fromToDate);
        mResumeEventOnClickListener = resumeEventOnClickListener;
        view.setOnClickListener(this);
    }

    public void onClick(View view) {
        this.mResumeEventOnClickListener.onClick(getAdapterPosition());
    }
}
