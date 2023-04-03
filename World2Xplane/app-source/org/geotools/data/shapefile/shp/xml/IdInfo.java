/*    */ package org.geotools.data.shapefile.shp.xml;
/*    */ 
/*    */ import com.vividsolutions.jts.geom.Envelope;
/*    */ 
/*    */ public class IdInfo {
/*    */   Envelope bounding;
/*    */   
/*    */   Envelope lbounding;
/*    */   
/*    */   public Envelope getBounding() {
/* 40 */     return this.bounding;
/*    */   }
/*    */   
/*    */   public void setBounding(Envelope bounding) {
/* 48 */     this.bounding = bounding;
/*    */   }
/*    */   
/*    */   public Envelope getLbounding() {
/* 55 */     return this.lbounding;
/*    */   }
/*    */   
/*    */   public void setLbounding(Envelope lbounding) {
/* 63 */     this.lbounding = lbounding;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\shapefile\shp\xml\IdInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */