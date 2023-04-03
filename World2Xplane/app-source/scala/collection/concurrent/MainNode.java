/*    */ package scala.collection.concurrent;
/*    */ 
/*    */ import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
/*    */ 
/*    */ abstract class MainNode<K, V> extends BasicNode {
/* 19 */   public static final AtomicReferenceFieldUpdater<MainNode, MainNode> updater = AtomicReferenceFieldUpdater.newUpdater(MainNode.class, MainNode.class, "prev");
/*    */   
/* 21 */   public volatile MainNode<K, V> prev = null;
/*    */   
/*    */   public abstract int cachedSize(Object paramObject);
/*    */   
/*    */   public boolean CAS_PREV(MainNode<K, V> oldval, MainNode<K, V> nval) {
/* 26 */     return updater.compareAndSet(this, oldval, nval);
/*    */   }
/*    */   
/*    */   public void WRITE_PREV(MainNode<K, V> nval) {
/* 30 */     updater.set(this, nval);
/*    */   }
/*    */   
/*    */   public MainNode<K, V> READ_PREV() {
/* 37 */     return updater.get(this);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\concurrent\MainNode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */