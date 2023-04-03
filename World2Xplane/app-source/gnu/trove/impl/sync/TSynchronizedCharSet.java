/*    */ package gnu.trove.impl.sync;
/*    */ 
/*    */ import gnu.trove.TCharCollection;
/*    */ import gnu.trove.set.TCharSet;
/*    */ 
/*    */ public class TSynchronizedCharSet extends TSynchronizedCharCollection implements TCharSet {
/*    */   private static final long serialVersionUID = 487447009682186044L;
/*    */   
/*    */   public TSynchronizedCharSet(TCharSet s) {
/* 58 */     super((TCharCollection)s);
/*    */   }
/*    */   
/*    */   public TSynchronizedCharSet(TCharSet s, Object mutex) {
/* 61 */     super((TCharCollection)s, mutex);
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


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\gnu\trove\impl\sync\TSynchronizedCharSet.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */