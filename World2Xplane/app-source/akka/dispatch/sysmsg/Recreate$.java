/*     */ package akka.dispatch.sysmsg;
/*     */ 
/*     */ import scala.Option;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ 
/*     */ public final class Recreate$ extends AbstractFunction1<Throwable, Recreate> implements Serializable {
/*     */   public static final Recreate$ MODULE$;
/*     */   
/*     */   public final String toString() {
/* 215 */     return "Recreate";
/*     */   }
/*     */   
/*     */   public Recreate apply(Throwable cause) {
/* 215 */     return new Recreate(cause);
/*     */   }
/*     */   
/*     */   public Option<Throwable> unapply(Recreate x$0) {
/* 215 */     return (x$0 == null) ? (Option<Throwable>)scala.None$.MODULE$ : (Option<Throwable>)new Some(x$0.cause());
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/* 215 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private Recreate$() {
/* 215 */     MODULE$ = this;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\dispatch\sysmsg\Recreate$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */