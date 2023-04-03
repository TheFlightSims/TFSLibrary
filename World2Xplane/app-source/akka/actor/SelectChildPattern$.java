/*     */ package akka.actor;
/*     */ 
/*     */ import scala.Option;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ 
/*     */ public final class SelectChildPattern$ extends AbstractFunction1<String, SelectChildPattern> implements Serializable {
/*     */   public static final SelectChildPattern$ MODULE$;
/*     */   
/*     */   public final String toString() {
/* 269 */     return "SelectChildPattern";
/*     */   }
/*     */   
/*     */   public SelectChildPattern apply(String patternStr) {
/* 269 */     return new SelectChildPattern(patternStr);
/*     */   }
/*     */   
/*     */   public Option<String> unapply(SelectChildPattern x$0) {
/* 269 */     return (x$0 == null) ? (Option<String>)scala.None$.MODULE$ : (Option<String>)new Some(x$0.patternStr());
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/* 269 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private SelectChildPattern$() {
/* 269 */     MODULE$ = this;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\SelectChildPattern$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */