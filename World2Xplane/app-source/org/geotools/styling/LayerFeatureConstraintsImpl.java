/*    */ package org.geotools.styling;
/*    */ 
/*    */ import java.util.Arrays;
/*    */ 
/*    */ public class LayerFeatureConstraintsImpl implements LayerFeatureConstraints {
/*    */   private FeatureTypeConstraint[] constraints;
/*    */   
/*    */   public FeatureTypeConstraint[] getFeatureTypeConstraints() {
/* 31 */     return this.constraints;
/*    */   }
/*    */   
/*    */   public void setFeatureTypeConstraints(FeatureTypeConstraint[] constraints) {
/* 35 */     this.constraints = constraints;
/*    */   }
/*    */   
/*    */   public boolean equals(Object obj) {
/* 39 */     if (this == obj)
/* 40 */       return true; 
/* 43 */     if (obj instanceof FeatureTypeConstraintImpl) {
/* 44 */       LayerFeatureConstraintsImpl other = (LayerFeatureConstraintsImpl)obj;
/* 45 */       return Arrays.equals((Object[])this.constraints, (Object[])other.constraints);
/*    */     } 
/* 48 */     return false;
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 52 */     int PRIME = 1000003;
/* 53 */     int result = 0;
/* 55 */     if (this.constraints != null)
/* 56 */       result = 1000003 * result + this.constraints.hashCode(); 
/* 59 */     return result;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\styling\LayerFeatureConstraintsImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */