package de.quarian.interval.engine;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;

public class Clockwork {

    private static Clock sMockedClock;
    private static Clock sSystemClock;

    @NonNull
    public static Clock getClock() {
        if (sMockedClock != null) {
            return sMockedClock;
        }
        if (sSystemClock == null) {
            sSystemClock = System::currentTimeMillis;
        }
        return sSystemClock;
    }

    @VisibleForTesting
    public static void setMockedClock(@Nullable Clock clock) {
        sMockedClock = clock;
    }
}
