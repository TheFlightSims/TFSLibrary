/*    */ package gnu.trove.impl.unmodifiable;
/*    */ 
/*    */ import gnu.trove.TByteCollection;
/*    */ import gnu.trove.set.TByteSet;
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class TUnmodifiableByteSet extends TUnmodifiableByteCollection implements TByteSet, Serializable {
/*    */   private static final long serialVersionUID = -9215047833775013803L;
/*    */   
/*    */   public TUnmodifiableByteSet(TByteSet s) {
/* 57 */     super((TByteCollection)s);
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


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\imp\\unmodifiable\TUnmodifiableByteSet.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */