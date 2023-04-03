/*    */ package scala.concurrent.impl;
/*    */ 
/*    */ import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
/*    */ import scala.concurrent.util.Unsafe;
/*    */ 
/*    */ abstract class AbstractPromise {
/*    */   private volatile Object _ref;
/*    */   
/*    */   static final long _refoffset;
/*    */   
/*    */   static {
/*    */     try {
/* 24 */       _refoffset = Unsafe.instance.objectFieldOffset(AbstractPromise.class.getDeclaredField("_ref"));
/* 25 */     } catch (Throwable t) {
/* 26 */       throw new ExceptionInInitializerError(t);
/*    */     } 
/*    */   }
/*    */   
/*    */   protected final boolean updateState(Object oldState, Object newState) {
/* 31 */     return Unsafe.instance.compareAndSwapObject(this, _refoffset, oldState, newState);
/*    */   }
/*    */   
/*    */   protected final Object getState() {
/* 35 */     return this._ref;
/*    */   }
/*    */   
/* 38 */   protected static final AtomicReferenceFieldUpdater<AbstractPromise, Object> updater = AtomicReferenceFieldUpdater.newUpdater(AbstractPromise.class, Object.class, "_ref");
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\concurrent\impl\AbstractPromise.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */