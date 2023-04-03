/*    */ package com.world2xplane.Rules;
/*    */ 
/*    */ import com.world2xplane.OSM.OSMPolygon;
/*    */ import java.util.Set;
/*    */ 
/*    */ public class AreaTrackerRule extends Rule {
/*    */   private String identifier;
/*    */   
/*    */   public AreaTrackerRule(GeneratorStore generatorStore) {
/* 34 */     super(generatorStore);
/*    */   }
/*    */   
/*    */   public Integer getDefinitionNumber(Object object) {
/* 39 */     return Integer.valueOf(0);
/*    */   }
/*    */   
/*    */   public boolean acceptsShape(Byte tagList, OSMPolygon shape, Set<String> regionsFromDsf, Rule.Delegate delegate, boolean acceptOnly) {
/* 47 */     if (shape == null)
/* 48 */       return false; 
/* 51 */     if (!validateShape(tagList, shape, regionsFromDsf, delegate))
/* 52 */       return false; 
/* 55 */     return true;
/*    */   }
/*    */   
/*    */   public String getIdentifier() {
/* 59 */     return this.identifier;
/*    */   }
/*    */   
/*    */   public void setIdentifier(String identifier) {
/* 63 */     this.identifier = identifier;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\Rules\AreaTrackerRule.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */