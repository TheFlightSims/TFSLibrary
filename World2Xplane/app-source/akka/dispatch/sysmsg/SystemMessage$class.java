/*     */ package akka.dispatch.sysmsg;
/*     */ 
/*     */ public abstract class SystemMessage$class {
/*     */   public static void $init$(SystemMessage $this) {}
/*     */   
/*     */   public static void unlink(SystemMessage $this) {
/* 191 */     null;
/* 191 */     $this.next_$eq(null);
/*     */   }
/*     */   
/*     */   public static boolean unlinked(SystemMessage $this) {
/* 193 */     return ($this.next() == null);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\dispatch\sysmsg\SystemMessage$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */