/*     */ package org.geotools.referencing.operation;
/*     */ 
/*     */ import java.util.Map;
/*     */ import org.opengis.referencing.crs.CoordinateReferenceSystem;
/*     */ import org.opengis.referencing.operation.ConicProjection;
/*     */ import org.opengis.referencing.operation.Conversion;
/*     */ import org.opengis.referencing.operation.CoordinateOperation;
/*     */ import org.opengis.referencing.operation.CylindricalProjection;
/*     */ import org.opengis.referencing.operation.MathTransform;
/*     */ import org.opengis.referencing.operation.Operation;
/*     */ import org.opengis.referencing.operation.OperationMethod;
/*     */ import org.opengis.referencing.operation.PlanarProjection;
/*     */ import org.opengis.referencing.operation.Projection;
/*     */ 
/*     */ public class DefaultConversion extends DefaultOperation implements Conversion {
/*     */   private static final long serialVersionUID = -2148164324805562793L;
/*     */   
/*     */   public DefaultConversion(Conversion definition, CoordinateReferenceSystem sourceCRS, CoordinateReferenceSystem targetCRS, MathTransform transform) {
/*  65 */     super(definition, sourceCRS, targetCRS, transform);
/*     */   }
/*     */   
/*     */   public DefaultConversion(Map<String, ?> properties, CoordinateReferenceSystem sourceCRS, CoordinateReferenceSystem targetCRS, MathTransform transform, OperationMethod method) {
/*  85 */     super(properties, sourceCRS, targetCRS, transform, method);
/*     */   }
/*     */   
/*     */   void validate() throws IllegalArgumentException {
/*  96 */     if (this.transform != null || this.sourceCRS != null || this.targetCRS != null)
/*  97 */       super.validate(); 
/*     */   }
/*     */   
/*     */   public static Conversion create(Conversion definition, CoordinateReferenceSystem sourceCRS, CoordinateReferenceSystem targetCRS, MathTransform transform, Class<? extends Conversion> typeHint) {
/*     */     Class<? extends Operation> clazz1;
/*     */     Class<? extends Conversion> clazz;
/* 131 */     Class<? extends CoordinateOperation> type = getType((CoordinateOperation)definition);
/* 132 */     OperationMethod method = definition.getMethod();
/* 133 */     if (method instanceof MathTransformProvider) {
/* 134 */       Class<? extends Operation> candidate = ((MathTransformProvider)method).getOperationType();
/* 135 */       if (candidate != null && 
/* 136 */         type.isAssignableFrom(candidate))
/* 137 */         clazz1 = candidate; 
/*     */     } 
/* 141 */     if (typeHint != null && clazz1.isAssignableFrom(typeHint))
/* 142 */       clazz = typeHint; 
/* 144 */     if (ConicProjection.class.isAssignableFrom(clazz))
/* 145 */       return new DefaultConicProjection(definition, sourceCRS, targetCRS, transform); 
/* 147 */     if (CylindricalProjection.class.isAssignableFrom(clazz))
/* 148 */       return new DefaultCylindricalProjection(definition, sourceCRS, targetCRS, transform); 
/* 150 */     if (PlanarProjection.class.isAssignableFrom(clazz))
/* 151 */       return new DefaultPlanarProjection(definition, sourceCRS, targetCRS, transform); 
/* 153 */     if (Projection.class.isAssignableFrom(clazz))
/* 154 */       return new DefaultProjection(definition, sourceCRS, targetCRS, transform); 
/* 156 */     return new DefaultConversion(definition, sourceCRS, targetCRS, transform);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\operation\DefaultConversion.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */