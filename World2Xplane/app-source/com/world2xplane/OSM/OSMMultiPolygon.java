/*    */ package com.world2xplane.OSM;
/*    */ 
/*    */ import java.util.List;
/*    */ 
/*    */ public class OSMMultiPolygon {
/*    */   private List<OSMRelation.Member> outerRings;
/*    */   
/*    */   private List<OSMRelation.Member> innerRings;
/*    */   
/*    */   public List<OSMRelation.Member> getOuterRings() {
/* 36 */     return this.outerRings;
/*    */   }
/*    */   
/*    */   public void setOuterRings(List<OSMRelation.Member> outerRings) {
/* 40 */     this.outerRings = outerRings;
/*    */   }
/*    */   
/*    */   public List<OSMRelation.Member> getInnerRings() {
/* 44 */     return this.innerRings;
/*    */   }
/*    */   
/*    */   public void setInnerRings(List<OSMRelation.Member> innerRings) {
/* 48 */     this.innerRings = innerRings;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\OSM\OSMMultiPolygon.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */