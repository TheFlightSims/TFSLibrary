/*    */ package gnu.trove.impl.sync;
/*    */ 
/*    */ import java.util.Set;
/*    */ 
/*    */ class SynchronizedSet<E> extends SynchronizedCollection<E> implements Set<E> {
/*    */   private static final long serialVersionUID = 487447009682186044L;
/*    */   
/*    */   SynchronizedSet(Set<E> s, Object mutex) {
/* 28 */     super(s, mutex);
/*    */   }
/*    */   
/*    */   public boolean equals(Object o) {
/* 29 */     synchronized (this.mutex) {
/* 29 */       return this.c.equals(o);
/*    */     } 
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 30 */     synchronized (this.mutex) {
/* 30 */       return this.c.hashCode();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\impl\sync\SynchronizedSet.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */