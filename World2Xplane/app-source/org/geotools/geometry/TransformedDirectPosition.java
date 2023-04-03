/*     */ package org.geotools.geometry;
/*     */ 
/*     */ import org.geotools.factory.FactoryRegistryException;
/*     */ import org.geotools.factory.Hints;
/*     */ import org.geotools.referencing.CRS;
/*     */ import org.geotools.referencing.ReferencingFactoryFinder;
/*     */ import org.geotools.referencing.crs.DefaultGeographicCRS;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ import org.opengis.geometry.DirectPosition;
/*     */ import org.opengis.geometry.MismatchedDimensionException;
/*     */ import org.opengis.referencing.FactoryException;
/*     */ import org.opengis.referencing.crs.CoordinateReferenceSystem;
/*     */ import org.opengis.referencing.operation.CoordinateOperation;
/*     */ import org.opengis.referencing.operation.CoordinateOperationFactory;
/*     */ import org.opengis.referencing.operation.MathTransform;
/*     */ import org.opengis.referencing.operation.TransformException;
/*     */ 
/*     */ public class TransformedDirectPosition extends GeneralDirectPosition {
/*     */   private static final long serialVersionUID = -3988283183934950437L;
/*     */   
/*     */   private final CoordinateOperationFactory factory;
/*     */   
/*     */   private final CoordinateReferenceSystem defaultCRS;
/*     */   
/*     */   private transient CoordinateReferenceSystem sourceCRS;
/*     */   
/*     */   private transient MathTransform forward;
/*     */   
/*     */   private transient MathTransform inverse;
/*     */   
/*     */   public TransformedDirectPosition() {
/* 132 */     this((CoordinateReferenceSystem)null, (CoordinateReferenceSystem)DefaultGeographicCRS.WGS84, (Hints)null);
/*     */   }
/*     */   
/*     */   public TransformedDirectPosition(CoordinateReferenceSystem sourceCRS, CoordinateReferenceSystem targetCRS, Hints hints) throws FactoryRegistryException {
/* 166 */     super(targetCRS);
/* 167 */     ensureNonNull("targetCRS", targetCRS);
/* 168 */     this.defaultCRS = CRS.equalsIgnoreMetadata(sourceCRS, targetCRS) ? null : sourceCRS;
/* 169 */     this.factory = ReferencingFactoryFinder.getCoordinateOperationFactory(hints);
/*     */   }
/*     */   
/*     */   public void setCoordinateReferenceSystem(CoordinateReferenceSystem crs) throws MismatchedDimensionException {
/* 191 */     ensureNonNull("crs", crs);
/* 192 */     super.setCoordinateReferenceSystem(crs);
/* 193 */     this.forward = null;
/* 194 */     this.inverse = null;
/*     */   }
/*     */   
/*     */   private void setSourceCRS(CoordinateReferenceSystem crs) throws TransformException {
/*     */     CoordinateOperation operation;
/* 202 */     CoordinateReferenceSystem targetCRS = getCoordinateReferenceSystem();
/*     */     try {
/* 205 */       operation = this.factory.createOperation(crs, targetCRS);
/* 206 */     } catch (FactoryException exception) {
/* 207 */       throw new TransformException(exception.getLocalizedMessage(), exception);
/*     */     } 
/* 214 */     this.forward = operation.getMathTransform();
/* 215 */     this.inverse = null;
/* 216 */     this.sourceCRS = crs;
/*     */   }
/*     */   
/*     */   public void transform(DirectPosition position) throws TransformException {
/* 242 */     CoordinateReferenceSystem userCRS = position.getCoordinateReferenceSystem();
/* 243 */     if (userCRS == null) {
/* 244 */       userCRS = this.defaultCRS;
/* 245 */       if (userCRS == null) {
/* 246 */         setLocation(position);
/*     */         return;
/*     */       } 
/*     */     } 
/* 256 */     if (this.forward == null || !CRS.equalsIgnoreMetadata(this.sourceCRS, userCRS))
/* 257 */       setSourceCRS(userCRS); 
/* 259 */     if (this.forward.transform(position, this) != this)
/* 260 */       throw new AssertionError(this.forward); 
/*     */   }
/*     */   
/*     */   public DirectPosition inverseTransform(CoordinateReferenceSystem crs) throws TransformException {
/* 278 */     if (this.inverse == null || !CRS.equalsIgnoreMetadata(this.sourceCRS, crs)) {
/* 279 */       ensureNonNull("crs", crs);
/* 280 */       setSourceCRS(crs);
/* 281 */       this.inverse = this.forward.inverse();
/*     */     } 
/* 283 */     return this.inverse.transform(this, null);
/*     */   }
/*     */   
/*     */   public DirectPosition inverseTransform() throws TransformException {
/* 298 */     if (this.defaultCRS != null)
/* 299 */       return inverseTransform(this.defaultCRS); 
/* 301 */     return new GeneralDirectPosition(this);
/*     */   }
/*     */   
/*     */   private static void ensureNonNull(String name, Object object) throws IllegalArgumentException {
/* 315 */     if (object == null)
/* 316 */       throw new IllegalArgumentException(Errors.format(143, name)); 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\geometry\TransformedDirectPosition.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */