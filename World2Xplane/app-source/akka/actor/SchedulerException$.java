/*    */ package akka.actor;
/*    */ 
/*    */ import scala.Option;
/*    */ import scala.Serializable;
/*    */ import scala.Some;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ 
/*    */ public final class SchedulerException$ extends AbstractFunction1<String, SchedulerException> implements Serializable {
/*    */   public static final SchedulerException$ MODULE$;
/*    */   
/*    */   public final String toString() {
/* 25 */     return "SchedulerException";
/*    */   }
/*    */   
/*    */   public SchedulerException apply(String msg) {
/* 25 */     return new SchedulerException(msg);
/*    */   }
/*    */   
/*    */   public Option<String> unapply(SchedulerException x$0) {
/* 25 */     return (x$0 == null) ? (Option<String>)scala.None$.MODULE$ : (Option<String>)new Some(x$0.msg());
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 25 */     return MODULE$;
/*    */   }
/*    */   
/*    */   private SchedulerException$() {
/* 25 */     MODULE$ = this;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\SchedulerException$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */