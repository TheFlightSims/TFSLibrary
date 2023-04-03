/*     */ package scala.collection.generic;
/*     */ 
/*     */ public abstract class DelegatedSignalling$class {
/*     */   public static void $init$(DelegatedSignalling $this) {}
/*     */   
/*     */   public static boolean isAborted(DelegatedSignalling $this) {
/* 165 */     return $this.signalDelegate().isAborted();
/*     */   }
/*     */   
/*     */   public static void abort(DelegatedSignalling $this) {
/* 166 */     $this.signalDelegate().abort();
/*     */   }
/*     */   
/*     */   public static int indexFlag(DelegatedSignalling $this) {
/* 168 */     return $this.signalDelegate().indexFlag();
/*     */   }
/*     */   
/*     */   public static void setIndexFlag(DelegatedSignalling $this, int f) {
/* 169 */     $this.signalDelegate().setIndexFlag(f);
/*     */   }
/*     */   
/*     */   public static void setIndexFlagIfGreater(DelegatedSignalling $this, int f) {
/* 170 */     $this.signalDelegate().setIndexFlagIfGreater(f);
/*     */   }
/*     */   
/*     */   public static void setIndexFlagIfLesser(DelegatedSignalling $this, int f) {
/* 171 */     $this.signalDelegate().setIndexFlagIfLesser(f);
/*     */   }
/*     */   
/*     */   public static int tag(DelegatedSignalling $this) {
/* 173 */     return $this.signalDelegate().tag();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\generic\DelegatedSignalling$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */