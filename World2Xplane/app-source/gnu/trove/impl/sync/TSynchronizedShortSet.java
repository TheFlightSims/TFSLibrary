/*    */ package gnu.trove.impl.sync;
/*    */ 
/*    */ import gnu.trove.TShortCollection;
/*    */ import gnu.trove.set.TShortSet;
/*    */ 
/*    */ public class TSynchronizedShortSet extends TSynchronizedShortCollection implements TShortSet {
/*    */   private static final long serialVersionUID = 487447009682186044L;
/*    */   
/*    */   public TSynchronizedShortSet(TShortSet s) {
/* 58 */     super((TShortCollection)s);
/*    */   }
/*    */   
/*    */   public TSynchronizedShortSet(TShortSet s, Object mutex) {
/* 61 */     super((TShortCollection)s, mutex);
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


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\impl\sync\TSynchronizedShortSet.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */