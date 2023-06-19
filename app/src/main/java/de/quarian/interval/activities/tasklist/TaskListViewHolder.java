package de.quarian.interval.activities.tasklist;

import android.view.View;
import android.widget.TextView;

import java.lang.ref.WeakReference;

import androidx.recyclerview.widget.RecyclerView;
import de.quarian.interval.R;

public class TaskListViewHolder extends RecyclerView.ViewHolder {

   private final WeakReference<TextView> mTitleTextViewWeakReference;

   public TaskListViewHolder(final View view) {
      super(view);
      final TextView titleTextView = view.findViewById(R.id.task_overview_list_item_title);
      mTitleTextViewWeakReference = new WeakReference<>(titleTextView);
   }

   public void setTitle(final String title) {
      final TextView textView = mTitleTextViewWeakReference.get();
      if (textView != null) {
         textView.setText(title);
      }
   }
}
