/*    */ package com.world2xplane.OSM;
/*    */ 
/*    */ import com.world2xplane.Rules.Rule;
/*    */ import java.util.List;
/*    */ 
/*    */ public class OSMShape {
/*    */   public Integer objectDefinitionNumber;
/*    */   
/*    */   public Integer customFacade;
/*    */   
/*    */   public Rule rule;
/*    */   
/*    */   public boolean simplify = false;
/*    */   
/*    */   public boolean part = false;
/*    */   
/*    */   public boolean multiPolygonPart = false;
/*    */   
/*    */   private byte requiredLevel;
/*    */   
/*    */   public OSMPolygon outer;
/*    */   
/*    */   public List<OSMPolygon> inner;
/*    */   
/*    */   public OSMShape(Rule rule) {
/* 39 */     this.rule = rule;
/*    */   }
/*    */   
/*    */   public Integer getObjectDefinitionNumber() {
/* 46 */     return this.objectDefinitionNumber;
/*    */   }
/*    */   
/*    */   public void setObjectDefinitionNumber(Integer objectDefinitionNumber) {
/* 50 */     this.objectDefinitionNumber = objectDefinitionNumber;
/*    */   }
/*    */   
/*    */   public void setRequiredLevel(byte requiredLevel) {
/* 55 */     this.requiredLevel = requiredLevel;
/*    */   }
/*    */   
/*    */   public byte getRequiredLevel() {
/* 59 */     return this.requiredLevel;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\OSM\OSMShape.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */