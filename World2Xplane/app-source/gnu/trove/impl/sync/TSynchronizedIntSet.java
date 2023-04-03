/*    */ package gnu.trove.impl.sync;
/*    */ 
/*    */ import gnu.trove.TIntCollection;
/*    */ import gnu.trove.set.TIntSet;
/*    */ 
/*    */ public class TSynchronizedIntSet extends TSynchronizedIntCollection implements TIntSet {
/*    */   private static final long serialVersionUID = 487447009682186044L;
/*    */   
/*    */   public TSynchronizedIntSet(TIntSet s) {
/* 58 */     super((TIntCollection)s);
/*    */   }
/*    */   
/*    */   public TSynchronizedIntSet(TIntSet s, Object mutex) {
/* 61 */     super((TIntCollection)s, mutex);
/*    */   }
/*    */   
/*    */   public boolean equals(Object o) {
/* 65 */     synchronized (this.mutex) {
/* 65 */       return this.c.equals(o);
/*    */     } 
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 68 */     synchronized (this.mutex) {
/* 68 */       return this.c.hashCode();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\impl\sync\TSynchronizedIntSet.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */