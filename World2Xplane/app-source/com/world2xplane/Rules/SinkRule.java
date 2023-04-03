/*    */ package com.world2xplane.Rules;
/*    */ 
/*    */ import com.world2xplane.OSM.OSMPolygon;
/*    */ import java.util.Set;
/*    */ 
/*    */ public class SinkRule extends Rule {
/* 32 */   private double minArea = -1.0D;
/*    */   
/* 33 */   private double maxArea = -1.0D;
/*    */   
/*    */   public SinkRule(GeneratorStore generatorStore) {
/* 37 */     super(generatorStore);
/*    */   }
/*    */   
/*    */   public Integer getDefinitionNumber(Object object) {
/* 42 */     return Integer.valueOf(0);
/*    */   }
/*    */   
/*    */   public boolean acceptsShape(Byte value, OSMPolygon shape, Set<String> regionsFromDsf, Rule.Delegate delegate, boolean acceptOnly) {
/* 52 */     if (shape == null)
/* 53 */       return false; 
/* 56 */     if (shape.getCustomFacade() != null)
/* 57 */       return false; 
/* 60 */     if (!validateShape(value, shape, regionsFromDsf, delegate))
/* 61 */       return false; 
/* 64 */     boolean accept = true;
/* 67 */     if (this.minArea != -1.0D || this.maxArea != -1.0D) {
/* 68 */       double area = shape.getArea().floatValue();
/* 69 */       if (area != 0.0D) {
/* 70 */         if (this.minArea != -1.0D && area < this.minArea * this.minArea)
/* 71 */           accept = false; 
/* 73 */         if (this.maxArea != -1.0D && area > this.maxArea * this.maxArea)
/* 74 */           accept = false; 
/*    */       } 
/*    */     } 
/* 80 */     return accept;
/*    */   }
/*    */   
/*    */   public double getMinArea() {
/* 84 */     return this.minArea;
/*    */   }
/*    */   
/*    */   public void setMinArea(double minArea) {
/* 88 */     this.minArea = minArea;
/*    */   }
/*    */   
/*    */   public double getMaxArea() {
/* 92 */     return this.maxArea;
/*    */   }
/*    */   
/*    */   public void setMaxArea(double maxArea) {
/* 96 */     this.maxArea = maxArea;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\Rules\SinkRule.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */