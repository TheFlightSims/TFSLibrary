/*    */ package scala.concurrent.forkjoin;
/*    */ 
/*    */ public abstract class RecursiveTask<V> extends ForkJoinTask<V> {
/*    */   private static final long serialVersionUID = 5232453952276485270L;
/*    */   
/*    */   V result;
/*    */   
/*    */   protected abstract V compute();
/*    */   
/*    */   public final V getRawResult() {
/* 53 */     return this.result;
/*    */   }
/*    */   
/*    */   protected final void setRawResult(V value) {
/* 57 */     this.result = value;
/*    */   }
/*    */   
/*    */   protected final boolean exec() {
/* 64 */     this.result = compute();
/* 65 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\concurrent\forkjoin\RecursiveTask.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */