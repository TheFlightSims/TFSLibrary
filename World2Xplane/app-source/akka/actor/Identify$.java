/*    */ package akka.actor;
/*    */ 
/*    */ import scala.Option;
/*    */ import scala.Serializable;
/*    */ import scala.Some;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ 
/*    */ public final class Identify$ extends AbstractFunction1<Object, Identify> implements Serializable {
/*    */   public static final Identify$ MODULE$;
/*    */   
/*    */   public final String toString() {
/* 63 */     return "Identify";
/*    */   }
/*    */   
/*    */   public Identify apply(Object messageId) {
/* 63 */     return new Identify(messageId);
/*    */   }
/*    */   
/*    */   public Option<Object> unapply(Identify x$0) {
/* 63 */     return (x$0 == null) ? (Option<Object>)scala.None$.MODULE$ : (Option<Object>)new Some(x$0.messageId());
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 63 */     return MODULE$;
/*    */   }
/*    */   
/*    */   private Identify$() {
/* 63 */     MODULE$ = this;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\Identify$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */