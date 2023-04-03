/*    */ package org.jfree.data.xy;
/*    */ 
/*    */ public abstract class AbstractXYZDataset extends AbstractXYDataset implements XYZDataset {
/*    */   public double getZValue(int series, int item) {
/* 63 */     double result = Double.NaN;
/* 64 */     Number z = getZ(series, item);
/* 65 */     if (z != null)
/* 66 */       result = z.doubleValue(); 
/* 68 */     return result;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\data\xy\AbstractXYZDataset.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */