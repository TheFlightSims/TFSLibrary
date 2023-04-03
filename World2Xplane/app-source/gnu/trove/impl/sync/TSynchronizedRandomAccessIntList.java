/*    */ package gnu.trove.impl.sync;
/*    */ 
/*    */ import gnu.trove.list.TIntList;
/*    */ import java.util.RandomAccess;
/*    */ 
/*    */ public class TSynchronizedRandomAccessIntList extends TSynchronizedIntList implements RandomAccess {
/*    */   static final long serialVersionUID = 1530674583602358482L;
/*    */   
/*    */   public TSynchronizedRandomAccessIntList(TIntList list) {
/* 58 */     super(list);
/*    */   }
/*    */   
/*    */   public TSynchronizedRandomAccessIntList(TIntList list, Object mutex) {
/* 62 */     super(list, mutex);
/*    */   }
/*    */   
/*    */   public TIntList subList(int fromIndex, int toIndex) {
/* 66 */     synchronized (this.mutex) {
/* 67 */       return new TSynchronizedRandomAccessIntList(this.list.subList(fromIndex, toIndex), this.mutex);
/*    */     } 
/*    */   }
/*    */   
/*    */   private Object writeReplace() {
/* 79 */     return new TSynchronizedIntList(this.list);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\impl\sync\TSynchronizedRandomAccessIntList.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */