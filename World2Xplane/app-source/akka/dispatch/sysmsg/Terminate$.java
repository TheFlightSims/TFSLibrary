/*     */ package akka.dispatch.sysmsg;
/*     */ 
/*     */ import scala.Serializable;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ 
/*     */ public final class Terminate$ extends AbstractFunction0<Terminate> implements Serializable {
/*     */   public static final Terminate$ MODULE$;
/*     */   
/*     */   public final String toString() {
/* 230 */     return "Terminate";
/*     */   }
/*     */   
/*     */   public Terminate apply() {
/* 230 */     return new Terminate();
/*     */   }
/*     */   
/*     */   public boolean unapply(Terminate x$0) {
/* 230 */     return !(x$0 == null);
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/* 230 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private Terminate$() {
/* 230 */     MODULE$ = this;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\dispatch\sysmsg\Terminate$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */