/*    */ package org.apache.commons.math3.optimization.fitting;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class WeightedObservedPoint implements Serializable {
/*    */   private static final long serialVersionUID = 5306874947404636157L;
/*    */   
/*    */   private final double weight;
/*    */   
/*    */   private final double x;
/*    */   
/*    */   private final double y;
/*    */   
/*    */   public WeightedObservedPoint(double weight, double x, double y) {
/* 48 */     this.weight = weight;
/* 49 */     this.x = x;
/* 50 */     this.y = y;
/*    */   }
/*    */   
/*    */   public double getWeight() {
/* 57 */     return this.weight;
/*    */   }
/*    */   
/*    */   public double getX() {
/* 64 */     return this.x;
/*    */   }
/*    */   
/*    */   public double getY() {
/* 71 */     return this.y;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\optimization\fitting\WeightedObservedPoint.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */