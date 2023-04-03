/*     */ package org.geotools.referencing.operation;
/*     */ 
/*     */ import java.util.Map;
/*     */ import org.geotools.referencing.operation.transform.PassThroughTransform;
/*     */ import org.geotools.referencing.wkt.Formatter;
/*     */ import org.geotools.util.UnsupportedImplementationException;
/*     */ import org.opengis.referencing.IdentifiedObject;
/*     */ import org.opengis.referencing.crs.CoordinateReferenceSystem;
/*     */ import org.opengis.referencing.operation.MathTransform;
/*     */ import org.opengis.referencing.operation.Operation;
/*     */ import org.opengis.referencing.operation.PassThroughOperation;
/*     */ 
/*     */ public class DefaultPassThroughOperation extends DefaultSingleOperation implements PassThroughOperation {
/*     */   private static final long serialVersionUID = 4308173919747248695L;
/*     */   
/*     */   protected final Operation operation;
/*     */   
/*     */   public DefaultPassThroughOperation(Map<String, ?> properties, CoordinateReferenceSystem sourceCRS, CoordinateReferenceSystem targetCRS, Operation operation, int firstAffectedOrdinate, int numTrailingOrdinates) {
/*  78 */     this(properties, sourceCRS, targetCRS, operation, PassThroughTransform.create(firstAffectedOrdinate, operation.getMathTransform(), numTrailingOrdinates));
/*     */   }
/*     */   
/*     */   public DefaultPassThroughOperation(Map<String, ?> properties, CoordinateReferenceSystem sourceCRS, CoordinateReferenceSystem targetCRS, Operation operation, MathTransform transform) {
/* 102 */     super(properties, sourceCRS, targetCRS, transform);
/* 103 */     this.operation = operation;
/* 104 */     ensureNonNull("operation", operation);
/* 105 */     ensureValidDimension(operation.getSourceCRS(), transform.getSourceDimensions());
/* 106 */     ensureValidDimension(operation.getTargetCRS(), transform.getTargetDimensions());
/*     */   }
/*     */   
/*     */   private static void ensureValidDimension(CoordinateReferenceSystem crs, int dim) {
/* 113 */     if (crs.getCoordinateSystem().getDimension() > dim)
/* 114 */       throw new IllegalArgumentException(); 
/*     */   }
/*     */   
/*     */   public Operation getOperation() {
/* 124 */     return this.operation;
/*     */   }
/*     */   
/*     */   public int[] getModifiedCoordinates() {
/* 137 */     if (!(this.transform instanceof PassThroughTransform))
/* 138 */       throw new UnsupportedImplementationException(this.transform.getClass()); 
/* 140 */     return ((PassThroughTransform)this.transform).getModifiedCoordinates();
/*     */   }
/*     */   
/*     */   protected String formatWKT(Formatter formatter) {
/* 148 */     String name = super.formatWKT(formatter);
/*     */     try {
/* 150 */       int[] ordinates = getModifiedCoordinates();
/* 151 */       for (int i = 0; i < ordinates.length; i++)
/* 152 */         formatter.append(ordinates[i]); 
/* 154 */     } catch (UnsupportedOperationException exception) {
/* 156 */       formatter.setInvalidWKT(PassThroughOperation.class);
/*     */     } 
/* 158 */     formatter.append((IdentifiedObject)this.operation);
/* 159 */     return name;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\operation\DefaultPassThroughOperation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */