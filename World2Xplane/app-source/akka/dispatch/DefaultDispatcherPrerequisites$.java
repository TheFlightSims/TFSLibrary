/*    */ package akka.dispatch;
/*    */ 
/*    */ import akka.actor.ActorSystem;
/*    */ import akka.actor.DynamicAccess;
/*    */ import akka.actor.Scheduler;
/*    */ import akka.event.EventStream;
/*    */ import java.util.concurrent.ThreadFactory;
/*    */ import scala.Option;
/*    */ import scala.Serializable;
/*    */ import scala.Some;
/*    */ import scala.Tuple7;
/*    */ import scala.concurrent.ExecutionContext;
/*    */ import scala.runtime.AbstractFunction7;
/*    */ 
/*    */ public final class DefaultDispatcherPrerequisites$ extends AbstractFunction7<ThreadFactory, EventStream, Scheduler, DynamicAccess, ActorSystem.Settings, Mailboxes, Option<ExecutionContext>, DefaultDispatcherPrerequisites> implements Serializable {
/*    */   public static final DefaultDispatcherPrerequisites$ MODULE$;
/*    */   
/*    */   public final String toString() {
/* 34 */     return "DefaultDispatcherPrerequisites";
/*    */   }
/*    */   
/*    */   public DefaultDispatcherPrerequisites apply(ThreadFactory threadFactory, EventStream eventStream, Scheduler scheduler, DynamicAccess dynamicAccess, ActorSystem.Settings settings, Mailboxes mailboxes, Option<ExecutionContext> defaultExecutionContext) {
/* 34 */     return new DefaultDispatcherPrerequisites(threadFactory, eventStream, scheduler, dynamicAccess, settings, mailboxes, defaultExecutionContext);
/*    */   }
/*    */   
/*    */   public Option<Tuple7<ThreadFactory, EventStream, Scheduler, DynamicAccess, ActorSystem.Settings, Mailboxes, Option<ExecutionContext>>> unapply(DefaultDispatcherPrerequisites x$0) {
/* 34 */     return (x$0 == null) ? (Option<Tuple7<ThreadFactory, EventStream, Scheduler, DynamicAccess, ActorSystem.Settings, Mailboxes, Option<ExecutionContext>>>)scala.None$.MODULE$ : (Option<Tuple7<ThreadFactory, EventStream, Scheduler, DynamicAccess, ActorSystem.Settings, Mailboxes, Option<ExecutionContext>>>)new Some(new Tuple7(x$0.threadFactory(), x$0.eventStream(), x$0.scheduler(), x$0.dynamicAccess(), x$0.settings(), x$0.mailboxes(), x$0.defaultExecutionContext()));
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 34 */     return MODULE$;
/*    */   }
/*    */   
/*    */   private DefaultDispatcherPrerequisites$() {
/* 34 */     MODULE$ = this;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\dispatch\DefaultDispatcherPrerequisites$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */