/*     */ package akka.actor;
/*     */ 
/*     */ import scala.Option;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ 
/*     */ public final class InvalidActorNameException$ extends AbstractFunction1<String, InvalidActorNameException> implements Serializable {
/*     */   public static final InvalidActorNameException$ MODULE$;
/*     */   
/*     */   public final String toString() {
/* 144 */     return "InvalidActorNameException";
/*     */   }
/*     */   
/*     */   public InvalidActorNameException apply(String message) {
/* 144 */     return new InvalidActorNameException(message);
/*     */   }
/*     */   
/*     */   public Option<String> unapply(InvalidActorNameException x$0) {
/* 144 */     return (x$0 == null) ? (Option<String>)scala.None$.MODULE$ : (Option<String>)new Some(x$0.message());
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/* 144 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private InvalidActorNameException$() {
/* 144 */     MODULE$ = this;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\InvalidActorNameException$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */