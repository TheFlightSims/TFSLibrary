/*    */ package gnu.trove.impl.sync;
/*    */ 
/*    */ import gnu.trove.TLongCollection;
/*    */ import gnu.trove.set.TLongSet;
/*    */ 
/*    */ public class TSynchronizedLongSet extends TSynchronizedLongCollection implements TLongSet {
/*    */   private static final long serialVersionUID = 487447009682186044L;
/*    */   
/*    */   public TSynchronizedLongSet(TLongSet s) {
/* 58 */     super((TLongCollection)s);
/*    */   }
/*    */   
/*    */   public TSynchronizedLongSet(TLongSet s, Object mutex) {
/* 61 */     super((TLongCollection)s, mutex);
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


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\impl\sync\TSynchronizedLongSet.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */