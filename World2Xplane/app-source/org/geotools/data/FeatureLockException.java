/*    */ package org.geotools.data;
/*    */ 
/*    */ import java.io.IOException;
/*    */ 
/*    */ public class FeatureLockException extends IOException {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   String featureID;
/*    */   
/*    */   public FeatureLockException() {
/* 37 */     this.featureID = null;
/*    */   }
/*    */   
/*    */   public FeatureLockException(String message) {
/* 41 */     super(message);
/* 42 */     this.featureID = null;
/*    */   }
/*    */   
/*    */   public FeatureLockException(String message, String featureID) {
/* 46 */     super(message);
/* 47 */     this.featureID = featureID;
/*    */   }
/*    */   
/*    */   public FeatureLockException(String message, Throwable t) {
/* 51 */     this(message, null, t);
/*    */   }
/*    */   
/*    */   public FeatureLockException(String message, String featureID, Throwable t) {
/* 55 */     super(message);
/* 56 */     initCause(t);
/* 57 */     this.featureID = featureID;
/*    */   }
/*    */   
/*    */   public String getFeatureID() {
/* 66 */     return this.featureID;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\FeatureLockException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */