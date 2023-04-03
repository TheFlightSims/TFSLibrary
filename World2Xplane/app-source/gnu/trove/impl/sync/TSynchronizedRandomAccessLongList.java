/*    */ package gnu.trove.impl.sync;
/*    */ 
/*    */ import gnu.trove.list.TLongList;
/*    */ import java.util.RandomAccess;
/*    */ 
/*    */ public class TSynchronizedRandomAccessLongList extends TSynchronizedLongList implements RandomAccess {
/*    */   static final long serialVersionUID = 1530674583602358482L;
/*    */   
/*    */   public TSynchronizedRandomAccessLongList(TLongList list) {
/* 58 */     super(list);
/*    */   }
/*    */   
/*    */   public TSynchronizedRandomAccessLongList(TLongList list, Object mutex) {
/* 62 */     super(list, mutex);
/*    */   }
/*    */   
/*    */   public TLongList subList(int fromIndex, int toIndex) {
/* 66 */     synchronized (this.mutex) {
/* 67 */       return new TSynchronizedRandomAccessLongList(this.list.subList(fromIndex, toIndex), this.mutex);
/*    */     } 
/*    */   }
/*    */   
/*    */   private Object writeReplace() {
/* 79 */     return new TSynchronizedLongList(this.list);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\impl\sync\TSynchronizedRandomAccessLongList.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */