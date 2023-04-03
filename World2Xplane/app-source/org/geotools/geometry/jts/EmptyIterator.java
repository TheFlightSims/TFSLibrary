/*    */ package org.geotools.geometry.jts;
/*    */ 
/*    */ public class EmptyIterator extends AbstractLiteIterator {
/* 32 */   public static final EmptyIterator INSTANCE = new EmptyIterator();
/*    */   
/*    */   public int getWindingRule() {
/* 35 */     return 1;
/*    */   }
/*    */   
/*    */   public boolean isDone() {
/* 39 */     return true;
/*    */   }
/*    */   
/*    */   public void next() {
/* 43 */     throw new IllegalStateException();
/*    */   }
/*    */   
/*    */   public int currentSegment(double[] coords) {
/* 47 */     return 0;
/*    */   }
/*    */   
/*    */   public int currentSegment(float[] coords) {
/* 51 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\geometry\jts\EmptyIterator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */