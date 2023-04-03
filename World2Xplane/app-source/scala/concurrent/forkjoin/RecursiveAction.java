/*     */ package scala.concurrent.forkjoin;
/*     */ 
/*     */ public abstract class RecursiveAction extends ForkJoinTask<Void> {
/*     */   private static final long serialVersionUID = 5232453952276485070L;
/*     */   
/*     */   public final Void getRawResult() {
/* 149 */     return null;
/*     */   }
/*     */   
/*     */   protected final void setRawResult(Void mustBeNull) {}
/*     */   
/*     */   protected final boolean exec() {
/* 160 */     compute();
/* 161 */     return true;
/*     */   }
/*     */   
/*     */   protected abstract void compute();
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\concurrent\forkjoin\RecursiveAction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */