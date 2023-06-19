package de.quarian.interval.activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import de.quarian.interval.R;
import de.quarian.interval.activities.tasklist.TaskListAdapter;
import de.quarian.interval.activities.tasklist.TaskListViewHolder;

public class TaskListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.task_overview);
        setUpRecyclerView();
    }

    private void setUpRecyclerView() {
        final RecyclerView recyclerView = findViewById(R.id.task_overview_task_list);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        final TaskListAdapter adapter = new TaskListAdapter(this);
        recyclerView.setAdapter(adapter);
        // adapter.notifyDataSetChanged();
    }

}
