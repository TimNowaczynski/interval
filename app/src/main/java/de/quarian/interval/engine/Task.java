package de.quarian.interval.engine;

import java.util.Timer;

import androidx.annotation.NonNull;

public interface Task {

    /*
        TODO:
            Activate/Pause timer elements based on activity lifecycle
            Reset displayed values on resume
            so we need to calculate
                elapsed time (currentMS - startMS)
                remaining time (property - elapsed time)
     */

    long elapsedMs();
    long remainingMs();

}
