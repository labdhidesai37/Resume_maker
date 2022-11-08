package testantony.resumemaker.cvmaker.profilecreate.Adapter;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import testantony.resumemaker.cvmaker.profilecreate.Model.ResumeEvent;
import testantony.resumemaker.cvmaker.profilecreate.R;

import java.util.List;

public abstract class CvResumeEventAdapter<T extends ResumeEvent> extends RecyclerView.Adapter<CvResumeEventAdapterViewHolder> {
    private final List<T> list;
    private final ResumeEventOnClickListener mResumeEventOnClickListener;

    public interface ResumeEventOnClickListener {
        void onClick(int i);
    }

    public void updateViewHolder(CvResumeEventAdapterViewHolder cvResumeEventAdapterViewHolder) {
    }

    public CvResumeEventAdapter(List<T> list2, ResumeEventOnClickListener resumeEventOnClickListener) {
        list = list2;
        mResumeEventOnClickListener = resumeEventOnClickListener;
    }

    @NonNull
    @Override
    public CvResumeEventAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        CvResumeEventAdapterViewHolder cvResumeEventAdapterViewHolder = new CvResumeEventAdapterViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_resume_event, viewGroup, false), mResumeEventOnClickListener);
        updateViewHolder(cvResumeEventAdapterViewHolder);
        return cvResumeEventAdapterViewHolder;
    }

    public CvResumeEventAdapter<T> setEmptyView(final View view) {
        registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            public void onChanged() {
                super.onChanged();
                if (getItemCount() == 0) {
                    view.setVisibility(View.VISIBLE);
                } else {
                    view.setVisibility(View.INVISIBLE);
                }
            }
        });
        return this;
    }

    @Override
    public void onBindViewHolder(CvResumeEventAdapterViewHolder cvResumeEventAdapterViewHolder, int i) {
        ResumeEvent resumeEvent = list.get(i);
        cvResumeEventAdapterViewHolder.itemView.setTag(i);
        cvResumeEventAdapterViewHolder.title.setText(resumeEvent.getTitle());
        cvResumeEventAdapterViewHolder.detail.setText(resumeEvent.getDetail());
        cvResumeEventAdapterViewHolder.subtitle.setText(resumeEvent.getSubtitle());
        cvResumeEventAdapterViewHolder.description.setText(resumeEvent.getDescription());
        cvResumeEventAdapterViewHolder.fromToDate.setText(Html.fromHtml(resumeEvent.getFromDate() + "- " + resumeEvent.getToDate()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
