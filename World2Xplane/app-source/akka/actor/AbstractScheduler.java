package akka.actor;

import scala.concurrent.ExecutionContext;
import scala.concurrent.duration.FiniteDuration;

public abstract class AbstractScheduler extends AbstractSchedulerBase {
  public abstract Cancellable schedule(FiniteDuration paramFiniteDuration1, FiniteDuration paramFiniteDuration2, Runnable paramRunnable, ExecutionContext paramExecutionContext);
  
  public abstract Cancellable scheduleOnce(FiniteDuration paramFiniteDuration, Runnable paramRunnable, ExecutionContext paramExecutionContext);
  
  public abstract double maxFrequency();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\AbstractScheduler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */