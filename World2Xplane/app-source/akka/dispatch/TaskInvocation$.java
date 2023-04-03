/*    */ package akka.dispatch;
/*    */ 
/*    */ import akka.event.EventStream;
/*    */ import scala.Function0;
/*    */ import scala.Option;
/*    */ import scala.Serializable;
/*    */ import scala.Some;
/*    */ import scala.Tuple3;
/*    */ import scala.runtime.AbstractFunction3;
/*    */ import scala.runtime.BoxedUnit;
/*    */ 
/*    */ public final class TaskInvocation$ extends AbstractFunction3<EventStream, Runnable, Function0<BoxedUnit>, TaskInvocation> implements Serializable {
/*    */   public static final TaskInvocation$ MODULE$;
/*    */   
/*    */   public final String toString() {
/* 33 */     return "TaskInvocation";
/*    */   }
/*    */   
/*    */   public TaskInvocation apply(EventStream eventStream, Runnable runnable, Function0<BoxedUnit> cleanup) {
/* 33 */     return new TaskInvocation(eventStream, runnable, cleanup);
/*    */   }
/*    */   
/*    */   public Option<Tuple3<EventStream, Runnable, Function0<BoxedUnit>>> unapply(TaskInvocation x$0) {
/* 33 */     return (x$0 == null) ? (Option<Tuple3<EventStream, Runnable, Function0<BoxedUnit>>>)scala.None$.MODULE$ : (Option<Tuple3<EventStream, Runnable, Function0<BoxedUnit>>>)new Some(new Tuple3(x$0.eventStream(), x$0.runnable(), x$0.cleanup()));
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 33 */     return MODULE$;
/*    */   }
/*    */   
/*    */   private TaskInvocation$() {
/* 33 */     MODULE$ = this;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\dispatch\TaskInvocation$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */