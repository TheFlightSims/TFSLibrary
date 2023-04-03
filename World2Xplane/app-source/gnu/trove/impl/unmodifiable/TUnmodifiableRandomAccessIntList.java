/*    */ package gnu.trove.impl.unmodifiable;
/*    */ 
/*    */ import gnu.trove.list.TIntList;
/*    */ import java.util.RandomAccess;
/*    */ 
/*    */ public class TUnmodifiableRandomAccessIntList extends TUnmodifiableIntList implements RandomAccess {
/*    */   private static final long serialVersionUID = -2542308836966382001L;
/*    */   
/*    */   public TUnmodifiableRandomAccessIntList(TIntList list) {
/* 58 */     super(list);
/*    */   }
/*    */   
/*    */   public TIntList subList(int fromIndex, int toIndex) {
/* 62 */     return new TUnmodifiableRandomAccessIntList(this.list.subList(fromIndex, toIndex));
/*    */   }
/*    */   
/*    */   private Object writeReplace() {
/* 72 */     return new TUnmodifiableIntList(this.list);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\imp\\unmodifiable\TUnmodifiableRandomAccessIntList.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */