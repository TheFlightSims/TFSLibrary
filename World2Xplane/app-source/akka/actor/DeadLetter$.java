/*     */ package akka.actor;
/*     */ 
/*     */ import scala.Option;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.Tuple3;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ import scala.runtime.AbstractFunction3;
/*     */ 
/*     */ public final class DeadLetter$ extends AbstractFunction3<Object, ActorRef, ActorRef, DeadLetter> implements Serializable {
/*     */   public static final DeadLetter$ MODULE$;
/*     */   
/*     */   public final String toString() {
/* 454 */     return "DeadLetter";
/*     */   }
/*     */   
/*     */   public DeadLetter apply(Object message, ActorRef sender, ActorRef recipient) {
/* 454 */     return new DeadLetter(message, sender, recipient);
/*     */   }
/*     */   
/*     */   public Option<Tuple3<Object, ActorRef, ActorRef>> unapply(DeadLetter x$0) {
/* 454 */     return (x$0 == null) ? (Option<Tuple3<Object, ActorRef, ActorRef>>)scala.None$.MODULE$ : (Option<Tuple3<Object, ActorRef, ActorRef>>)new Some(new Tuple3(x$0.message(), x$0.sender(), x$0.recipient()));
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/* 454 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private DeadLetter$() {
/* 454 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public class DeadLetter$$anonfun$1 extends AbstractFunction0<String> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final String apply() {
/* 455 */       return "DeadLetter sender may not be null";
/*     */     }
/*     */     
/*     */     public DeadLetter$$anonfun$1(DeadLetter $outer) {}
/*     */   }
/*     */   
/*     */   public class DeadLetter$$anonfun$2 extends AbstractFunction0<String> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final String apply() {
/* 456 */       return "DeadLetter recipient may not be null";
/*     */     }
/*     */     
/*     */     public DeadLetter$$anonfun$2(DeadLetter $outer) {}
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\DeadLetter$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */