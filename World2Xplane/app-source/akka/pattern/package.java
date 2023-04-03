package akka.pattern;

import akka.actor.ActorRef;
import akka.actor.ActorSelection;
import akka.actor.Scheduler;
import akka.util.Timeout;
import scala.Function0;
import scala.concurrent.ExecutionContext;
import scala.concurrent.Future;
import scala.concurrent.duration.FiniteDuration;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001\005:Q!\001\002\t\002\035\tq\001]1dW\006<WM\003\002\004\t\0059\001/\031;uKJt'\"A\003\002\t\005\\7.Y\002\001!\tA\021\"D\001\003\r\025Q!\001#\001\f\005\035\001\030mY6bO\026\034b!\003\007\023+aY\002CA\007\021\033\005q!\"A\b\002\013M\034\027\r\\1\n\005Eq!AB!osJ+g\r\005\002\t'%\021AC\001\002\016!&\004X\rV8TkB\004xN\035;\021\005!1\022BA\f\003\005)\t5o[*vaB|'\017\036\t\003\021eI!A\007\002\003'\035\023\030mY3gk2\034Fo\0349TkB\004xN\035;\021\005!a\022BA\017\003\005Q1U\017^;sKRKW.Z8viN+\b\017]8si\")q$\003C\001A\0051A(\0338jiz\"\022a\002")
public final class package {
  public static <T> PipeToSupport.PipeableFuture<T> pipe(Future<T> paramFuture, ExecutionContext paramExecutionContext) {
    return package$.MODULE$.pipe(paramFuture, paramExecutionContext);
  }
  
  public static Future<Object> ask(ActorSelection paramActorSelection, Object paramObject, Timeout paramTimeout) {
    return package$.MODULE$.ask(paramActorSelection, paramObject, paramTimeout);
  }
  
  public static ActorSelection ask(ActorSelection paramActorSelection) {
    return package$.MODULE$.ask(paramActorSelection);
  }
  
  public static Future<Object> ask(ActorRef paramActorRef, Object paramObject, Timeout paramTimeout) {
    return package$.MODULE$.ask(paramActorRef, paramObject, paramTimeout);
  }
  
  public static ActorRef ask(ActorRef paramActorRef) {
    return package$.MODULE$.ask(paramActorRef);
  }
  
  public static Object gracefulStop$default$3() {
    return package$.MODULE$.gracefulStop$default$3();
  }
  
  public static Future<Object> gracefulStop(ActorRef paramActorRef, FiniteDuration paramFiniteDuration, Object paramObject) {
    return package$.MODULE$.gracefulStop(paramActorRef, paramFiniteDuration, paramObject);
  }
  
  public static <T> Future<T> after(FiniteDuration paramFiniteDuration, Scheduler paramScheduler, Function0<Future<T>> paramFunction0, ExecutionContext paramExecutionContext) {
    return package$.MODULE$.after(paramFiniteDuration, paramScheduler, paramFunction0, paramExecutionContext);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\pattern\package.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */