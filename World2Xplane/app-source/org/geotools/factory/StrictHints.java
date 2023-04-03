/*    */ package org.geotools.factory;
/*    */ 
/*    */ import java.awt.RenderingHints;
/*    */ import java.util.Map;
/*    */ 
/*    */ class StrictHints extends Hints {
/*    */   public StrictHints(Hints hints) {
/* 37 */     super(hints);
/*    */   }
/*    */   
/*    */   public StrictHints(Map<RenderingHints.Key, Object> hints) {
/* 44 */     super(hints);
/*    */   }
/*    */   
/*    */   static final class Empty extends StrictHints {
/*    */     Empty() {
/* 55 */       super((Hints)null);
/*    */     }
/*    */     
/*    */     public void add(RenderingHints hints) {
/* 63 */       throw new UnsupportedOperationException();
/*    */     }
/*    */     
/*    */     public Object put(Object key, Object value) {
/* 71 */       throw new UnsupportedOperationException();
/*    */     }
/*    */     
/*    */     public void putAll(Map<?, ?> map) {
/* 79 */       throw new UnsupportedOperationException();
/*    */     }
/*    */     
/*    */     public StrictHints clone() {
/* 87 */       return new StrictHints((Hints)null);
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\factory\StrictHints.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */