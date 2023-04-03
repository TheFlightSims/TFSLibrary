/*    */ package gnu.trove.impl.unmodifiable;
/*    */ 
/*    */ import gnu.trove.TCharCollection;
/*    */ import gnu.trove.set.TCharSet;
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class TUnmodifiableCharSet extends TUnmodifiableCharCollection implements TCharSet, Serializable {
/*    */   private static final long serialVersionUID = -9215047833775013803L;
/*    */   
/*    */   public TUnmodifiableCharSet(TCharSet s) {
/* 57 */     super((TCharCollection)s);
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


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\imp\\unmodifiable\TUnmodifiableCharSet.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */