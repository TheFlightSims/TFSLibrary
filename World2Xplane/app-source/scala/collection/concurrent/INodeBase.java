/*    */ package scala.collection.concurrent;
/*    */ 
/*    */ import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
/*    */ 
/*    */ abstract class INodeBase<K, V> extends BasicNode {
/* 19 */   public static final AtomicReferenceFieldUpdater<INodeBase, MainNode> updater = AtomicReferenceFieldUpdater.newUpdater(INodeBase.class, MainNode.class, "mainnode");
/*    */   
/* 21 */   public static final Object RESTART = new Object();
/*    */   
/* 23 */   public volatile MainNode<K, V> mainnode = null;
/*    */   
/*    */   public final Gen gen;
/*    */   
/*    */   public INodeBase(Gen generation) {
/* 28 */     this.gen = generation;
/*    */   }
/*    */   
/*    */   public BasicNode prev() {
/* 32 */     return null;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\concurrent\INodeBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */