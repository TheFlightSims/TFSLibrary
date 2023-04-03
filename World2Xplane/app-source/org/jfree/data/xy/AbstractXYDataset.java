/*    */ package org.jfree.data.xy;
/*    */ 
/*    */ import org.jfree.data.DomainOrder;
/*    */ import org.jfree.data.general.AbstractSeriesDataset;
/*    */ 
/*    */ public abstract class AbstractXYDataset extends AbstractSeriesDataset implements XYDataset {
/*    */   public DomainOrder getDomainOrder() {
/* 64 */     return DomainOrder.NONE;
/*    */   }
/*    */   
/*    */   public double getXValue(int series, int item) {
/* 76 */     double result = Double.NaN;
/* 77 */     Number x = getX(series, item);
/* 78 */     if (x != null)
/* 79 */       result = x.doubleValue(); 
/* 81 */     return result;
/*    */   }
/*    */   
/*    */   public double getYValue(int series, int item) {
/* 93 */     double result = Double.NaN;
/* 94 */     Number y = getY(series, item);
/* 95 */     if (y != null)
/* 96 */       result = y.doubleValue(); 
/* 98 */     return result;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\data\xy\AbstractXYDataset.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */