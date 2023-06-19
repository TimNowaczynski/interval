package de.quarian.interval.activities.tasklist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.ref.WeakReference;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import de.quarian.interval.R;
import de.quarian.interval.activities.TaskListActivity;

public class TaskListAdapter extends RecyclerView.Adapter<TaskListViewHolder> {

   private final WeakReference<Context> mContextWeakReference;

   public TaskListAdapter(@NonNull final Context context) {
      mContextWeakReference = new WeakReference<>(context);
   }

   @NonNull
   @Override
   public TaskListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      final Context context = mContextWeakReference.get();
      final LayoutInflater layoutInflater = LayoutInflater.from(context);
      final View view = layoutInflater.inflate(R.layout.task_overview_list_item, null);
      return new TaskListViewHolder(view);
   }

   @Override
   public void onBindViewHolder(@NonNull TaskListViewHolder holder, int position) {
      holder.setTitle("Title " + (position + 1));
   }

   @Override
   public int getItemCount() {
      return 3;
   }
}
