/*     */ package scala.collection.generic;
/*     */ 
/*     */ public abstract class VolatileAbort$class {
/*     */   public static void $init$(VolatileAbort $this) {
/* 122 */     $this.scala$collection$generic$VolatileAbort$$abortflag_$eq(false);
/*     */   }
/*     */   
/*     */   public static boolean isAborted(VolatileAbort $this) {
/* 123 */     return $this.scala$collection$generic$VolatileAbort$$abortflag();
/*     */   }
/*     */   
/*     */   public static void abort(VolatileAbort $this) {
/* 124 */     $this.scala$collection$generic$VolatileAbort$$abortflag_$eq(true);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\generic\VolatileAbort$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */