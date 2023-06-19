package de.quarian.interval;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.stubbing.Answer;

import de.quarian.interval.engine.Clock;
import de.quarian.interval.engine.Clockwork;
import de.quarian.interval.engine.IntervalEngine;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(JUnit4.class)
public class IntervalEngineUnitTest {

   private IntervalEngine mIntervalEngine;

   @Before
   public void setUp() {
      final Clock mockedClock = mock(Clock.class);
      Clockwork.setMockedClock(mockedClock);
      mIntervalEngine = IntervalEngine.getInstance();

      when(mockedClock.currentTimeMillis()).then((Answer<Long>) invocation -> System.currentTimeMillis());
   }

   @Test
   public void testInstanceCreation() {
      final IntervalEngine instance = IntervalEngine.getInstance();
      assertNotNull(instance);
   }

   @Test
   public void testEnqueueTask() {
      // just to ensure we can fail
      assertNotNull(null);
   }

}
