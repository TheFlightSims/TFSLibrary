/*    */ package gnu.trove.impl.sync;
/*    */ 
/*    */ import gnu.trove.list.TDoubleList;
/*    */ import java.util.RandomAccess;
/*    */ 
/*    */ public class TSynchronizedRandomAccessDoubleList extends TSynchronizedDoubleList implements RandomAccess {
/*    */   static final long serialVersionUID = 1530674583602358482L;
/*    */   
/*    */   public TSynchronizedRandomAccessDoubleList(TDoubleList list) {
/* 58 */     super(list);
/*    */   }
/*    */   
/*    */   public TSynchronizedRandomAccessDoubleList(TDoubleList list, Object mutex) {
/* 62 */     super(list, mutex);
/*    */   }
/*    */   
/*    */   public TDoubleList subList(int fromIndex, int toIndex) {
/* 66 */     synchronized (this.mutex) {
/* 67 */       return new TSynchronizedRandomAccessDoubleList(this.list.subList(fromIndex, toIndex), this.mutex);
/*    */     } 
/*    */   }
/*    */   
/*    */   private Object writeReplace() {
/* 79 */     return new TSynchronizedDoubleList(this.list);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\impl\sync\TSynchronizedRandomAccessDoubleList.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */