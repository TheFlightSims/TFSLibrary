/*    */ package gnu.trove.impl.unmodifiable;
/*    */ 
/*    */ import gnu.trove.list.TFloatList;
/*    */ import java.util.RandomAccess;
/*    */ 
/*    */ public class TUnmodifiableRandomAccessFloatList extends TUnmodifiableFloatList implements RandomAccess {
/*    */   private static final long serialVersionUID = -2542308836966382001L;
/*    */   
/*    */   public TUnmodifiableRandomAccessFloatList(TFloatList list) {
/* 58 */     super(list);
/*    */   }
/*    */   
/*    */   public TFloatList subList(int fromIndex, int toIndex) {
/* 62 */     return new TUnmodifiableRandomAccessFloatList(this.list.subList(fromIndex, toIndex));
/*    */   }
/*    */   
/*    */   private Object writeReplace() {
/* 72 */     return new TUnmodifiableFloatList(this.list);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\imp\\unmodifiable\TUnmodifiableRandomAccessFloatList.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */