/*    */ package com.world2xplane.Rules;
/*    */ 
/*    */ import com.world2xplane.OSM.OSMPolygon;
/*    */ import java.util.Set;
/*    */ 
/*    */ public class NodeTrackerRule extends Rule {
/*    */   private String identifier;
/*    */   
/*    */   public NodeTrackerRule(GeneratorStore generatorStore) {
/* 56 */     super(generatorStore);
/*    */   }
/*    */   
/*    */   public Integer getDefinitionNumber(Object object) {
/* 61 */     return Integer.valueOf(0);
/*    */   }
/*    */   
/*    */   public boolean acceptsShape(Byte tagList, OSMPolygon shape, Set<String> possibleRegions, Rule.Delegate delegate, boolean acceptOnly) {
/* 67 */     if (shape != null)
/* 68 */       return true; 
/* 70 */     return false;
/*    */   }
/*    */   
/*    */   public String getIdentifier() {
/* 74 */     return this.identifier;
/*    */   }
/*    */   
/*    */   public void setIdentifier(String identifier) {
/* 78 */     this.identifier = identifier;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\Rules\NodeTrackerRule.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */