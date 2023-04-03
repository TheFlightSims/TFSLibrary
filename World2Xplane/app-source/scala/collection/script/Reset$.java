/*     */ package scala.collection.script;
/*     */ 
/*     */ import scala.Serializable;
/*     */ 
/*     */ public final class Reset$ implements Serializable {
/*     */   public static final Reset$ MODULE$;
/*     */   
/*     */   public final String toString() {
/*  60 */     return "Reset";
/*     */   }
/*     */   
/*     */   public <A> Reset<A> apply() {
/*  60 */     return new Reset<A>();
/*     */   }
/*     */   
/*     */   public <A> boolean unapply(Reset x$0) {
/*  60 */     return !(x$0 == null);
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/*  60 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private Reset$() {
/*  60 */     MODULE$ = this;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\script\Reset$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */