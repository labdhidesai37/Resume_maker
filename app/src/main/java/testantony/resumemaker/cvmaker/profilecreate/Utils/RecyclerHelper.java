package testantony.resumemaker.cvmaker.profilecreate.Utils;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import testantony.resumemaker.cvmaker.profilecreate.Interface.OnDragListener;
import testantony.resumemaker.cvmaker.profilecreate.Interface.OnSwipeListener;

import java.util.ArrayList;
import java.util.Collections;

public final class RecyclerHelper<T> extends ItemTouchHelper.Callback {
    private boolean isItemDragEnabled;
    private boolean isItemSwipeEnbled;
    private ArrayList<T> list;
    private RecyclerView.Adapter<RecyclerView.ViewHolder> mAdapter;
    private OnDragListener onDragListener;
    private OnSwipeListener onSwipeListener;

    public boolean isItemViewSwipeEnabled() {
        return true;
    }

    public boolean isLongPressDragEnabled() {
        return true;
    }

    public RecyclerHelper(ArrayList<T> arrayList, RecyclerView.Adapter<RecyclerView.ViewHolder> adapter) {
        this.list = arrayList;
        this.mAdapter = adapter;
    }

    public final ArrayList<T> getList() {
        return this.list;
    }

    public final RecyclerView.Adapter<RecyclerView.ViewHolder> getMAdapter() {
        return this.mAdapter;
    }

    public final void setList(ArrayList<T> arrayList) {
        this.list = arrayList;
    }

    public final void setMAdapter(RecyclerView.Adapter<RecyclerView.ViewHolder> adapter) {
        this.mAdapter = adapter;
    }

    public final OnDragListener getOnDragListener() {
        return this.onDragListener;
    }

    public final void setOnDragListener(OnDragListener onDragListener2) {
        this.onDragListener = onDragListener2;
    }

    public final OnSwipeListener getOnSwipeListener() {
        return this.onSwipeListener;
    }

    public final void setOnSwipeListener(OnSwipeListener onSwipeListener2) {
        this.onSwipeListener = onSwipeListener2;
    }

    public final void onMoved(int i, int i2) {
        this.list.remove(i2);
        this.mAdapter.notifyItemRemoved(i2);
    }

    public final void onItemMoved(int i, int i2) {
        Collections.swap(this.list, i, i2);
        this.mAdapter.notifyItemMoved(i, i2);
        OnDragListener onDragListener2 = this.onDragListener;
        if (onDragListener2 != null) {
            onDragListener2.onDragItemListener(i, i2);
        }
    }

    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        int i = 0;
        int i2 = this.isItemDragEnabled ? 3 : 0;
        if (this.isItemSwipeEnbled) {
            i = 12;
        }
        return ItemTouchHelper.Callback.makeMovementFlags(i2, i);
    }

    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder2) {
        onItemMoved(viewHolder.getAdapterPosition(), viewHolder2.getAdapterPosition());
        return true;
    }

    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        onMoved(viewHolder.getOldPosition(), viewHolder.getAdapterPosition());
        OnSwipeListener onSwipeListener2 = this.onSwipeListener;
        if (onSwipeListener2 != null) {
            onSwipeListener2.onSwipeItemListener();
        }
    }

    public final RecyclerHelper<T> setRecyclerItemDragEnabled(boolean z) {
        this.isItemDragEnabled = z;
        return this;
    }

    public final RecyclerHelper<T> setRecyclerItemSwipeEnabled(boolean z) {
        this.isItemSwipeEnbled = z;
        return this;
    }

    public final RecyclerHelper<T> setOnDragItemListener(OnDragListener onDragListener2) {
        this.onDragListener = onDragListener2;
        return this;
    }

    public final RecyclerHelper<T> setOnSwipeItemListener(OnSwipeListener onSwipeListener2) {
        this.onSwipeListener = onSwipeListener2;
        return this;
    }

    public final ArrayList<T> getFinalList() {
        return this.list;
    }
}
