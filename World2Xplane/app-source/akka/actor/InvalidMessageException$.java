/*     */ package akka.actor;
/*     */ 
/*     */ import scala.Option;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ 
/*     */ public final class InvalidMessageException$ extends AbstractFunction1<String, InvalidMessageException> implements Serializable {
/*     */   public static final InvalidMessageException$ MODULE$;
/*     */   
/*     */   public final String toString() {
/* 223 */     return "InvalidMessageException";
/*     */   }
/*     */   
/*     */   public InvalidMessageException apply(String message) {
/* 223 */     return new InvalidMessageException(message);
/*     */   }
/*     */   
/*     */   public Option<String> unapply(InvalidMessageException x$0) {
/* 223 */     return (x$0 == null) ? (Option<String>)scala.None$.MODULE$ : (Option<String>)new Some(x$0.message());
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/* 223 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private InvalidMessageException$() {
/* 223 */     MODULE$ = this;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\InvalidMessageException$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */