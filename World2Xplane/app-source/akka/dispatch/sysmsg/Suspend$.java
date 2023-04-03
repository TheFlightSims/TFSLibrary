/*     */ package akka.dispatch.sysmsg;
/*     */ 
/*     */ import scala.Serializable;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ 
/*     */ public final class Suspend$ extends AbstractFunction0<Suspend> implements Serializable {
/*     */   public static final Suspend$ MODULE$;
/*     */   
/*     */   public final String toString() {
/* 220 */     return "Suspend";
/*     */   }
/*     */   
/*     */   public Suspend apply() {
/* 220 */     return new Suspend();
/*     */   }
/*     */   
/*     */   public boolean unapply(Suspend x$0) {
/* 220 */     return !(x$0 == null);
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/* 220 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private Suspend$() {
/* 220 */     MODULE$ = this;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\dispatch\sysmsg\Suspend$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */