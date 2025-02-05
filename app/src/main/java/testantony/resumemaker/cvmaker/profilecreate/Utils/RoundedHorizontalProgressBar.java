package testantony.resumemaker.cvmaker.profilecreate.Utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ScaleDrawable;
import android.util.AttributeSet;
import android.widget.ProgressBar;

import androidx.core.content.res.ResourcesCompat;

import testantony.resumemaker.cvmaker.profilecreate.R;

public class RoundedHorizontalProgressBar extends ProgressBar {
    private int mBackgroundColor = -7829368;
    private boolean mIsRounded = true;
    private int mProgressColor = -16776961;

    public RoundedHorizontalProgressBar(Context context) {
        super(context);
        init();
    }

    public RoundedHorizontalProgressBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet);
    }

    public RoundedHorizontalProgressBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context, attributeSet);
    }

    private void init() {
        LayerDrawable layerDrawable;
        if (this.mIsRounded) {
            layerDrawable = (LayerDrawable) ResourcesCompat.getDrawable(getResources(), R.drawable.rounded_progress_bar_horizontal, null);
        } else {
            layerDrawable = (LayerDrawable) ResourcesCompat.getDrawable(getResources(), R.drawable.progress_bar_horizontal, null);
        }
        setProgressDrawable(layerDrawable);
        setProgressColors(this.mBackgroundColor, this.mProgressColor);
    }

    private void init(Context context, AttributeSet attributeSet) {
        LayerDrawable layerDrawable;
        setUpStyleable(context, attributeSet);
        if (this.mIsRounded) {
            layerDrawable = (LayerDrawable) ResourcesCompat.getDrawable(getResources(), R.drawable.rounded_progress_bar_horizontal, null);
        } else {
            layerDrawable = (LayerDrawable) ResourcesCompat.getDrawable(getResources(), R.drawable.progress_bar_horizontal, null);
        }
        setProgressDrawable(layerDrawable);
        setProgressColors(this.mBackgroundColor, this.mProgressColor);
    }

    @SuppressLint("ResourceType")
    private void setUpStyleable(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.RoundedHorizontalProgress);
        this.mBackgroundColor = obtainStyledAttributes.getColor(0, -7829368);
        this.mProgressColor = obtainStyledAttributes.getColor(2, -16776961);
        this.mIsRounded = obtainStyledAttributes.getBoolean(1, true);
        obtainStyledAttributes.recycle();
    }

    public void setProgressColors(int i, int i2) {
        LayerDrawable layerDrawable = (LayerDrawable) getProgressDrawable();
        ((GradientDrawable) layerDrawable.findDrawableByLayerId(16908288)).setColor(i);
        if (this.mIsRounded) {
            ((GradientDrawable) ((ScaleDrawable) layerDrawable.findDrawableByLayerId(16908301)).getDrawable()).setColor(i2);
            setProgressDrawable(layerDrawable);
            return;
        }
        layerDrawable.findDrawableByLayerId(16908301).setColorFilter(i2, PorterDuff.Mode.MULTIPLY);
        setProgressDrawable(layerDrawable);
    }


    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
    }
}
