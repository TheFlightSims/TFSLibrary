/*    */ package scala.collection.concurrent;
/*    */ 
/*    */ import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
/*    */ 
/*    */ abstract class CNodeBase<K, V> extends MainNode<K, V> {
/* 19 */   public static final AtomicIntegerFieldUpdater<CNodeBase> updater = AtomicIntegerFieldUpdater.newUpdater(CNodeBase.class, "csize");
/*    */   
/* 21 */   public volatile int csize = -1;
/*    */   
/*    */   public boolean CAS_SIZE(int oldval, int nval) {
/* 24 */     return updater.compareAndSet(this, oldval, nval);
/*    */   }
/*    */   
/*    */   public void WRITE_SIZE(int nval) {
/* 28 */     updater.set(this, nval);
/*    */   }
/*    */   
/*    */   public int READ_SIZE() {
/* 32 */     return updater.get(this);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\concurrent\CNodeBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */