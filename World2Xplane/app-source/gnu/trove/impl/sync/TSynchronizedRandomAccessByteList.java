/*    */ package gnu.trove.impl.sync;
/*    */ 
/*    */ import gnu.trove.list.TByteList;
/*    */ import java.util.RandomAccess;
/*    */ 
/*    */ public class TSynchronizedRandomAccessByteList extends TSynchronizedByteList implements RandomAccess {
/*    */   static final long serialVersionUID = 1530674583602358482L;
/*    */   
/*    */   public TSynchronizedRandomAccessByteList(TByteList list) {
/* 58 */     super(list);
/*    */   }
/*    */   
/*    */   public TSynchronizedRandomAccessByteList(TByteList list, Object mutex) {
/* 62 */     super(list, mutex);
/*    */   }
/*    */   
/*    */   public TByteList subList(int fromIndex, int toIndex) {
/* 66 */     synchronized (this.mutex) {
/* 67 */       return new TSynchronizedRandomAccessByteList(this.list.subList(fromIndex, toIndex), this.mutex);
/*    */     } 
/*    */   }
/*    */   
/*    */   private Object writeReplace() {
/* 79 */     return new TSynchronizedByteList(this.list);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\impl\sync\TSynchronizedRandomAccessByteList.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */