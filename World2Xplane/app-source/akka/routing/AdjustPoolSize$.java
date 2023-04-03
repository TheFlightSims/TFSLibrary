/*     */ package akka.routing;
/*     */ 
/*     */ import scala.Option;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ public final class AdjustPoolSize$ extends AbstractFunction1<Object, AdjustPoolSize> implements Serializable {
/*     */   public static final AdjustPoolSize$ MODULE$;
/*     */   
/*     */   public final String toString() {
/* 420 */     return "AdjustPoolSize";
/*     */   }
/*     */   
/*     */   public AdjustPoolSize apply(int change) {
/* 420 */     return new AdjustPoolSize(change);
/*     */   }
/*     */   
/*     */   public Option<Object> unapply(AdjustPoolSize x$0) {
/* 420 */     return (x$0 == null) ? (Option<Object>)scala.None$.MODULE$ : (Option<Object>)new Some(BoxesRunTime.boxToInteger(x$0.change()));
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/* 420 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private AdjustPoolSize$() {
/* 420 */     MODULE$ = this;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\routing\AdjustPoolSize$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */