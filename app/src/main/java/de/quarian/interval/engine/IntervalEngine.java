package de.quarian.interval.engine;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class IntervalEngine {

    private static IntervalEngine mIntervalEngine;

    @NonNull
    public static IntervalEngine getInstance() {
        if (mIntervalEngine == null) {
            mIntervalEngine = new IntervalEngine();
        }
        return mIntervalEngine;
    }

    private final LinkedList<Task> mTasks = new LinkedList<>();

    private IntervalEngine() {
        // nothing yet
    }

    public void enqueueTask(@NonNull Task task) {
        mTasks.add(task);
    }

    public void removeTask(@NonNull Task task) {
        mTasks.remove(task);
    }

    /**
     * @return A copy of the task list
     */
    public List<Task> getTasks() {
        return new ArrayList<>(mTasks);
    }

    /**
     * Tasks further down are activated later
     * @param task the target task
     */
    public void sortDown(@NonNull Task task) {
        if (!mTasks.contains(task)) {
            return;
        }

        final int currentIndex = mTasks.indexOf(task);
        final int targetIndex = currentIndex + 2;
        final int size = mTasks.size();
        if (targetIndex >= size) {
            return;
        }

        mTasks.add(targetIndex, task);
        mTasks.remove(currentIndex);
    }

    /**
     * Tasks further up are activated earlier
     * @param task the target task
     */
    public void sortUp(@NonNull Task task) {
        if (!mTasks.contains(task)) {
            return;
        }

        final int currentIndex = mTasks.indexOf(task);
        if (currentIndex == 0) {
            return;
        }

        final int targetIndex = currentIndex - 1;
        mTasks.add(targetIndex, task);
        mTasks.remove(currentIndex + 1);
    }

    /**
     * @return The upcoming task, if any. Removes started items from the que.
     */
    @Nullable
    public Task pullNextTask() {
        final Task peekedTask = mTasks.peek();
        if (peekedTask == null) {
            return null;
        }

        if (peekedTask.remainingMs() > 0) {
            return peekedTask;
        }

        return mTasks.pop();
    }
}
