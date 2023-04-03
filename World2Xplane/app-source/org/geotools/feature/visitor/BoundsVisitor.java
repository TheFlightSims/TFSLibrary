/*    */ package org.geotools.feature.visitor;
/*    */ 
/*    */ import com.vividsolutions.jts.geom.Envelope;
/*    */ import org.geotools.geometry.jts.ReferencedEnvelope;
/*    */ import org.opengis.feature.Feature;
/*    */ import org.opengis.geometry.BoundingBox;
/*    */ 
/*    */ public class BoundsVisitor implements FeatureCalc {
/* 35 */   ReferencedEnvelope bounds = new ReferencedEnvelope();
/*    */   
/*    */   public void visit(Feature feature) {
/* 38 */     this.bounds.include(feature.getBounds());
/*    */   }
/*    */   
/*    */   public ReferencedEnvelope getBounds() {
/* 42 */     return this.bounds;
/*    */   }
/*    */   
/*    */   public void reset(Envelope bounds) {
/* 46 */     this.bounds = new ReferencedEnvelope();
/*    */   }
/*    */   
/*    */   public CalcResult getResult() {
/* 50 */     if (this.bounds == null || this.bounds.isEmpty())
/* 51 */       return CalcResult.NULL_RESULT; 
/* 53 */     return new BoundsResult(this.bounds);
/*    */   }
/*    */   
/*    */   public static class BoundsResult extends AbstractCalcResult {
/*    */     private ReferencedEnvelope bbox;
/*    */     
/*    */     public BoundsResult(ReferencedEnvelope bbox) {
/* 60 */       this.bbox = bbox;
/*    */     }
/*    */     
/*    */     public ReferencedEnvelope getValue() {
/* 64 */       return new ReferencedEnvelope(this.bbox);
/*    */     }
/*    */     
/*    */     public boolean isCompatible(CalcResult targetResults) {
/* 69 */       if (targetResults instanceof BoundsResult || targetResults == CalcResult.NULL_RESULT)
/* 70 */         return true; 
/* 73 */       return false;
/*    */     }
/*    */     
/*    */     public CalcResult merge(CalcResult resultsToAdd) {
/* 77 */       if (!isCompatible(resultsToAdd))
/* 78 */         throw new IllegalArgumentException("Parameter is not a compatible type"); 
/* 82 */       if (resultsToAdd == CalcResult.NULL_RESULT)
/* 83 */         return this; 
/* 86 */       if (resultsToAdd instanceof BoundsResult) {
/* 87 */         BoundsResult boundsToAdd = (BoundsResult)resultsToAdd;
/* 90 */         ReferencedEnvelope newBounds = new ReferencedEnvelope(this.bbox);
/* 91 */         newBounds.include((BoundingBox)boundsToAdd.getValue());
/* 93 */         return new BoundsResult(newBounds);
/*    */       } 
/* 95 */       throw new IllegalArgumentException("The CalcResults claim to be compatible, but the appropriate merge method has not been implemented.");
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\feature\visitor\BoundsVisitor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */