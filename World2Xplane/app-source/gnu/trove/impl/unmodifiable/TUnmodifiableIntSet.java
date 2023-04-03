/*    */ package gnu.trove.impl.unmodifiable;
/*    */ 
/*    */ import gnu.trove.TIntCollection;
/*    */ import gnu.trove.set.TIntSet;
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class TUnmodifiableIntSet extends TUnmodifiableIntCollection implements TIntSet, Serializable {
/*    */   private static final long serialVersionUID = -9215047833775013803L;
/*    */   
/*    */   public TUnmodifiableIntSet(TIntSet s) {
/* 57 */     super((TIntCollection)s);
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


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\imp\\unmodifiable\TUnmodifiableIntSet.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */