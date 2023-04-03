/*    */ package org.apache.commons.math3.filter;
/*    */ 
/*    */ import org.apache.commons.math3.linear.Array2DRowRealMatrix;
/*    */ import org.apache.commons.math3.linear.RealMatrix;
/*    */ 
/*    */ public class DefaultMeasurementModel implements MeasurementModel {
/*    */   private RealMatrix measurementMatrix;
/*    */   
/*    */   private RealMatrix measurementNoise;
/*    */   
/*    */   public DefaultMeasurementModel(double[][] measMatrix, double[][] measNoise) {
/* 51 */     this((RealMatrix)new Array2DRowRealMatrix(measMatrix), (RealMatrix)new Array2DRowRealMatrix(measNoise));
/*    */   }
/*    */   
/*    */   public DefaultMeasurementModel(RealMatrix measMatrix, RealMatrix measNoise) {
/* 64 */     this.measurementMatrix = measMatrix;
/* 65 */     this.measurementNoise = measNoise;
/*    */   }
/*    */   
/*    */   public RealMatrix getMeasurementMatrix() {
/* 70 */     return this.measurementMatrix;
/*    */   }
/*    */   
/*    */   public RealMatrix getMeasurementNoise() {
/* 75 */     return this.measurementNoise;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\filter\DefaultMeasurementModel.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */