/*     */ package akka.actor;
/*     */ 
/*     */ import scala.Option;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ 
/*     */ public final class IllegalActorStateException$ extends AbstractFunction1<String, IllegalActorStateException> implements Serializable {
/*     */   public static final IllegalActorStateException$ MODULE$;
/*     */   
/*     */   public final String toString() {
/* 131 */     return "IllegalActorStateException";
/*     */   }
/*     */   
/*     */   public IllegalActorStateException apply(String message) {
/* 131 */     return new IllegalActorStateException(message);
/*     */   }
/*     */   
/*     */   public Option<String> unapply(IllegalActorStateException x$0) {
/* 131 */     return (x$0 == null) ? (Option<String>)scala.None$.MODULE$ : (Option<String>)new Some(x$0.message());
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/* 131 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private IllegalActorStateException$() {
/* 131 */     MODULE$ = this;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\IllegalActorStateException$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */