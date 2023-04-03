/*    */ package org.geotools.referencing.operation;
/*    */ 
/*    */ import java.util.Collection;
/*    */ import java.util.Set;
/*    */ import org.opengis.metadata.extent.Extent;
/*    */ import org.opengis.metadata.quality.PositionalAccuracy;
/*    */ import org.opengis.referencing.ReferenceIdentifier;
/*    */ import org.opengis.referencing.crs.CoordinateReferenceSystem;
/*    */ import org.opengis.referencing.operation.CoordinateOperation;
/*    */ import org.opengis.referencing.operation.MathTransform;
/*    */ import org.opengis.util.GenericName;
/*    */ import org.opengis.util.InternationalString;
/*    */ 
/*    */ class ForcedCRSOperation implements CoordinateOperation {
/*    */   CoordinateOperation delegate;
/*    */   
/*    */   CoordinateReferenceSystem sourceCRS;
/*    */   
/*    */   CoordinateReferenceSystem targetCRS;
/*    */   
/*    */   public ForcedCRSOperation(CoordinateOperation delegate, CoordinateReferenceSystem sourceCRS, CoordinateReferenceSystem targetCRS) {
/* 34 */     this.delegate = delegate;
/* 35 */     this.sourceCRS = sourceCRS;
/* 36 */     this.targetCRS = targetCRS;
/*    */   }
/*    */   
/*    */   public CoordinateReferenceSystem getSourceCRS() {
/* 40 */     return this.sourceCRS;
/*    */   }
/*    */   
/*    */   public CoordinateReferenceSystem getTargetCRS() {
/* 44 */     return this.targetCRS;
/*    */   }
/*    */   
/*    */   public ReferenceIdentifier getName() {
/* 48 */     return this.delegate.getName();
/*    */   }
/*    */   
/*    */   public Collection<GenericName> getAlias() {
/* 52 */     return this.delegate.getAlias();
/*    */   }
/*    */   
/*    */   public Set<ReferenceIdentifier> getIdentifiers() {
/* 56 */     return this.delegate.getIdentifiers();
/*    */   }
/*    */   
/*    */   public InternationalString getRemarks() {
/* 60 */     return this.delegate.getRemarks();
/*    */   }
/*    */   
/*    */   public String toWKT() throws UnsupportedOperationException {
/* 64 */     return this.delegate.toWKT();
/*    */   }
/*    */   
/*    */   public String getOperationVersion() {
/* 68 */     return this.delegate.getOperationVersion();
/*    */   }
/*    */   
/*    */   public Collection<PositionalAccuracy> getCoordinateOperationAccuracy() {
/* 72 */     return this.delegate.getCoordinateOperationAccuracy();
/*    */   }
/*    */   
/*    */   public Extent getDomainOfValidity() {
/* 76 */     return this.delegate.getDomainOfValidity();
/*    */   }
/*    */   
/*    */   public InternationalString getScope() {
/* 80 */     return this.delegate.getScope();
/*    */   }
/*    */   
/*    */   public MathTransform getMathTransform() {
/* 84 */     return this.delegate.getMathTransform();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\operation\ForcedCRSOperation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */