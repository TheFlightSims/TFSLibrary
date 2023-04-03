/*    */ package gnu.trove.impl.unmodifiable;
/*    */ 
/*    */ import gnu.trove.list.TLongList;
/*    */ import java.util.RandomAccess;
/*    */ 
/*    */ public class TUnmodifiableRandomAccessLongList extends TUnmodifiableLongList implements RandomAccess {
/*    */   private static final long serialVersionUID = -2542308836966382001L;
/*    */   
/*    */   public TUnmodifiableRandomAccessLongList(TLongList list) {
/* 58 */     super(list);
/*    */   }
/*    */   
/*    */   public TLongList subList(int fromIndex, int toIndex) {
/* 62 */     return new TUnmodifiableRandomAccessLongList(this.list.subList(fromIndex, toIndex));
/*    */   }
/*    */   
/*    */   private Object writeReplace() {
/* 72 */     return new TUnmodifiableLongList(this.list);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\imp\\unmodifiable\TUnmodifiableRandomAccessLongList.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */