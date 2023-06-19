package de.quarian.interval.engine;

import java.lang.ref.WeakReference;
import java.util.Timer;
import java.util.TimerTask;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class TaskImplementation implements Task {

   // Display
   @NonNull
   private final String mName;
   @NonNull
   private final String mImageUrl;
   private final int mBackgroundColor;
   private final int mTextColor;

   // Timing
   private final long mStartMs;
   private final long mDurationMs;

   public TaskImplementation(@NonNull final String name,
                             @NonNull final String imageUrl,
                             @ColorInt final int backgroundColor,
                             @ColorInt final int textColor,
                             final long startMs,
                             final long durationMs) {
      mName = name;
      mImageUrl = imageUrl;
      mBackgroundColor = backgroundColor;
      mTextColor = textColor;
      mStartMs = startMs;
      mDurationMs = durationMs;
   }

   @Override
   public long elapsedMs() {
      return Clockwork.getClock().currentTimeMillis() - mStartMs;
   }

   @Override
   public long remainingMs() {
      final long elapsedMs = elapsedMs();
      return (mStartMs + mDurationMs) - elapsedMs;
   }
}
