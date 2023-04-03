/*    */ package com.vividsolutions.jtsexample.precision;
/*    */ 
/*    */ import com.vividsolutions.jts.geom.Geometry;
/*    */ import com.vividsolutions.jts.geom.TopologyException;
/*    */ import com.vividsolutions.jts.io.WKTReader;
/*    */ import com.vividsolutions.jts.precision.EnhancedPrecisionOp;
/*    */ 
/*    */ public class EnhancedPrecisionOpExample {
/*    */   public static void main(String[] args) throws Exception {
/* 49 */     EnhancedPrecisionOpExample example = new EnhancedPrecisionOpExample();
/*    */     try {
/* 51 */       example.run();
/* 53 */     } catch (Exception ex) {
/* 54 */       ex.printStackTrace();
/*    */     } 
/*    */   }
/*    */   
/* 58 */   private WKTReader reader = new WKTReader();
/*    */   
/*    */   void run() throws Exception {
/* 68 */     String wkt1 = "POLYGON ((708653.498611049 2402311.54647056, 708708.895756966 2402203.47250014, 708280.326454234 2402089.6337791, 708247.896591321 2402252.48269854, 708367.379593851 2402324.00761653, 708248.882609455 2402253.07294874, 708249.523621829 2402244.3124463, 708261.854734465 2402182.39086576, 708262.818392579 2402183.35452387, 708653.498611049 2402311.54647056))";
/* 69 */     String wkt2 = "POLYGON ((708258.754920656 2402197.91172757, 708257.029447455 2402206.56901508, 708652.961095455 2402312.65463437, 708657.068786251 2402304.6356364, 708258.754920656 2402197.91172757))";
/* 70 */     Geometry g1 = this.reader.read(wkt1);
/* 71 */     Geometry g2 = this.reader.read(wkt2);
/* 73 */     System.out.println("This call to intersection will throw a topology exception due to robustness problems:");
/*    */     try {
/* 75 */       Geometry geometry = g1.intersection(g2);
/* 77 */     } catch (TopologyException ex) {
/* 78 */       ex.printStackTrace();
/*    */     } 
/* 81 */     System.out.println("Using EnhancedPrecisionOp allows the intersection to be performed with no errors:");
/* 82 */     Geometry result = EnhancedPrecisionOp.intersection(g1, g2);
/* 83 */     System.out.println(result);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jtsexample\precision\EnhancedPrecisionOpExample.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */