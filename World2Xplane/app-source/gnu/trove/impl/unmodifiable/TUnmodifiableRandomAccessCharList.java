/*    */ package gnu.trove.impl.unmodifiable;
/*    */ 
/*    */ import gnu.trove.list.TCharList;
/*    */ import java.util.RandomAccess;
/*    */ 
/*    */ public class TUnmodifiableRandomAccessCharList extends TUnmodifiableCharList implements RandomAccess {
/*    */   private static final long serialVersionUID = -2542308836966382001L;
/*    */   
/*    */   public TUnmodifiableRandomAccessCharList(TCharList list) {
/* 58 */     super(list);
/*    */   }
/*    */   
/*    */   public TCharList subList(int fromIndex, int toIndex) {
/* 62 */     return new TUnmodifiableRandomAccessCharList(this.list.subList(fromIndex, toIndex));
/*    */   }
/*    */   
/*    */   private Object writeReplace() {
/* 72 */     return new TUnmodifiableCharList(this.list);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\imp\\unmodifiable\TUnmodifiableRandomAccessCharList.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */