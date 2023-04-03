/*    */ package gnu.trove.impl.unmodifiable;
/*    */ 
/*    */ import gnu.trove.TDoubleCollection;
/*    */ import gnu.trove.set.TDoubleSet;
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class TUnmodifiableDoubleSet extends TUnmodifiableDoubleCollection implements TDoubleSet, Serializable {
/*    */   private static final long serialVersionUID = -9215047833775013803L;
/*    */   
/*    */   public TUnmodifiableDoubleSet(TDoubleSet s) {
/* 57 */     super((TDoubleCollection)s);
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


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\imp\\unmodifiable\TUnmodifiableDoubleSet.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */