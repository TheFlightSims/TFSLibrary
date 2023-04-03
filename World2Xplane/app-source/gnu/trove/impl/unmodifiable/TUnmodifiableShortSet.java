/*    */ package gnu.trove.impl.unmodifiable;
/*    */ 
/*    */ import gnu.trove.TShortCollection;
/*    */ import gnu.trove.set.TShortSet;
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class TUnmodifiableShortSet extends TUnmodifiableShortCollection implements TShortSet, Serializable {
/*    */   private static final long serialVersionUID = -9215047833775013803L;
/*    */   
/*    */   public TUnmodifiableShortSet(TShortSet s) {
/* 57 */     super((TShortCollection)s);
/*    */   }
/*    */   
/*    */   public boolean equals(Object o) {
/* 58 */     return (o == this || this.c.equals(o));
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 59 */     return this.c.hashCode();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\imp\\unmodifiable\TUnmodifiableShortSet.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */