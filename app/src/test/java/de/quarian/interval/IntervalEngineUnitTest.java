package de.quarian.interval;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.stubbing.Answer;

import java.util.List;

import de.quarian.interval.engine.Clock;
import de.quarian.interval.engine.Clockwork;
import de.quarian.interval.engine.IntervalEngine;
import de.quarian.interval.engine.Task;

import static org.junit.Assert.assertEquals;
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
      assertNotNull(mIntervalEngine);
   }

   @Test
   public void testEnqueueTask() {
      List<Task> tasks = mIntervalEngine.getTasks();
      assertEquals(0, tasks.size());

      final Task task = mock(Task.class);
      mIntervalEngine.enqueueTask(task);

      tasks = mIntervalEngine.getTasks();
      assertEquals(1, tasks.size());
   }

   @Test
   public void testRemoveTask() {
      final Task task = mock(Task.class);
      mIntervalEngine.enqueueTask(task);

      List<Task> tasks = mIntervalEngine.getTasks();
      assertEquals(1, tasks.size());

      mIntervalEngine.removeTask(task);

      tasks = mIntervalEngine.getTasks();
      assertEquals(0, tasks.size());
   }

   @Test
   public void testMoveTaskUp() {
      final Task taskA = mock(Task.class);
      final Task taskB = mock(Task.class);
      final Task taskC = mock(Task.class);

      mIntervalEngine.enqueueTask(taskA);
      mIntervalEngine.enqueueTask(taskB);
      mIntervalEngine.enqueueTask(taskC);

      List<Task> tasks = mIntervalEngine.getTasks();
      assertEquals(taskA, tasks.get(0));
      assertEquals(taskB, tasks.get(1));
      assertEquals(taskC, tasks.get(2));

      mIntervalEngine.sortUp(taskC);

      tasks = mIntervalEngine.getTasks();
      assertEquals(taskA, tasks.get(0));
      assertEquals(taskC, tasks.get(1));
      assertEquals(taskB, tasks.get(2));
   }

   @Test
   public void testDoNotMoveTaskUpIfAlreadyUpmost() {
      final Task taskA = mock(Task.class);
      final Task taskB = mock(Task.class);
      final Task taskC = mock(Task.class);

      mIntervalEngine.enqueueTask(taskA);
      mIntervalEngine.enqueueTask(taskB);
      mIntervalEngine.enqueueTask(taskC);

      List<Task> tasks = mIntervalEngine.getTasks();
      assertEquals(taskA, tasks.get(0));
      assertEquals(taskB, tasks.get(1));
      assertEquals(taskC, tasks.get(2));

      mIntervalEngine.sortUp(taskA);

      tasks = mIntervalEngine.getTasks();
      assertEquals(taskA, tasks.get(0));
      assertEquals(taskB, tasks.get(1));
      assertEquals(taskC, tasks.get(2));
   }

   @Test
   public void testMoveTaskDown() {
      final Task taskA = mock(Task.class);
      final Task taskB = mock(Task.class);
      final Task taskC = mock(Task.class);

      mIntervalEngine.enqueueTask(taskA);
      mIntervalEngine.enqueueTask(taskB);
      mIntervalEngine.enqueueTask(taskC);

      List<Task> tasks = mIntervalEngine.getTasks();
      assertEquals(taskA, tasks.get(0));
      assertEquals(taskB, tasks.get(1));
      assertEquals(taskC, tasks.get(2));

      mIntervalEngine.sortDown(taskA);

      tasks = mIntervalEngine.getTasks();
      assertEquals(taskB, tasks.get(0));
      assertEquals(taskA, tasks.get(1));
      assertEquals(taskC, tasks.get(2));
   }

   @Test
   public void testDoNotMoveTaskDownIfAlreadyLast() {
      final Task taskA = mock(Task.class);
      final Task taskB = mock(Task.class);
      final Task taskC = mock(Task.class);

      mIntervalEngine.enqueueTask(taskA);
      mIntervalEngine.enqueueTask(taskB);
      mIntervalEngine.enqueueTask(taskC);

      List<Task> tasks = mIntervalEngine.getTasks();
      assertEquals(taskA, tasks.get(0));
      assertEquals(taskB, tasks.get(1));
      assertEquals(taskC, tasks.get(2));

      mIntervalEngine.sortDown(taskC);

      tasks = mIntervalEngine.getTasks();
      assertEquals(taskA, tasks.get(0));
      assertEquals(taskB, tasks.get(1));
      assertEquals(taskC, tasks.get(2));
   }

   @Test
   public void testPullNextTask() {
      final Task taskA = mock(Task.class);
      final Task taskB = mock(Task.class);

      mIntervalEngine.enqueueTask(taskA);
      mIntervalEngine.enqueueTask(taskB);

      List<Task> tasks = mIntervalEngine.getTasks();
      assertEquals(2, tasks.size());

      final Task pulledTask = mIntervalEngine.pullNextTask();
      assertEquals(taskA, pulledTask);

      tasks = mIntervalEngine.getTasks();
      assertEquals(1, tasks.size());
   }
}
