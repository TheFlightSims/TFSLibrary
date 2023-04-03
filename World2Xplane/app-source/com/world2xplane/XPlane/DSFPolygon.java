/*    */ package com.world2xplane.XPlane;
/*    */ 
/*    */ import com.world2xplane.OSM.OSMPolygon;
/*    */ import com.world2xplane.OSM.OSMRelation;
/*    */ import com.world2xplane.Rules.AcceptingRule;
/*    */ import java.util.List;
/*    */ 
/*    */ public class DSFPolygon {
/*    */   private List<OSMPolygon> inner;
/*    */   
/*    */   private List<AcceptingRule> rules;
/*    */   
/*    */   private OSMPolygon outer;
/*    */   
/*    */   private OSMRelation relation;
/*    */   
/*    */   public OSMPolygon getOuter() {
/* 39 */     return this.outer;
/*    */   }
/*    */   
/*    */   public void setOuter(OSMPolygon outer) {
/* 43 */     this.outer = outer;
/*    */   }
/*    */   
/*    */   public List<OSMPolygon> getInner() {
/* 47 */     return this.inner;
/*    */   }
/*    */   
/*    */   public void setInner(List<OSMPolygon> inner) {
/* 51 */     this.inner = inner;
/*    */   }
/*    */   
/*    */   public List<AcceptingRule> getRules() {
/* 55 */     return this.rules;
/*    */   }
/*    */   
/*    */   public void setRules(List<AcceptingRule> rules) {
/* 59 */     this.rules = rules;
/*    */   }
/*    */   
/*    */   public OSMRelation getRelation() {
/* 63 */     return this.relation;
/*    */   }
/*    */   
/*    */   public void setRelation(OSMRelation relation) {
/* 67 */     this.relation = relation;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\XPlane\DSFPolygon.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */