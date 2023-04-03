/*     */ package scala.collection.mutable;
/*     */ 
/*     */ import scala.Serializable;
/*     */ 
/*     */ public final class StringBuilder$ implements Serializable {
/*     */   public static final StringBuilder$ MODULE$;
/*     */   
/*     */   private StringBuilder$() {
/* 442 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/* 442 */     return MODULE$;
/*     */   }
/*     */   
/*     */   public StringBuilder newBuilder() {
/* 443 */     return new StringBuilder();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\StringBuilder$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */