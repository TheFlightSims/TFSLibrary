/*     */ package org.geotools.referencing.operation;
/*     */ 
/*     */ import java.util.Map;
/*     */ import org.geotools.referencing.AbstractIdentifiedObject;
/*     */ import org.geotools.referencing.operation.transform.AbstractMathTransform;
/*     */ import org.geotools.referencing.operation.transform.ConcatenatedTransform;
/*     */ import org.geotools.referencing.operation.transform.PassThroughTransform;
/*     */ import org.geotools.referencing.wkt.Formatter;
/*     */ import org.geotools.util.UnsupportedImplementationException;
/*     */ import org.opengis.parameter.ParameterDescriptorGroup;
/*     */ import org.opengis.parameter.ParameterValueGroup;
/*     */ import org.opengis.referencing.IdentifiedObject;
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
/*     */ import org.opengis.referencing.operation.Transformation;
/*     */ 
/*     */ public class DefaultOperation extends DefaultSingleOperation implements Operation {
/*     */   private static final long serialVersionUID = -8923365753849532179L;
/*     */   
/*     */   protected final OperationMethod method;
/*     */   
/*     */   DefaultOperation(Conversion definition, CoordinateReferenceSystem sourceCRS, CoordinateReferenceSystem targetCRS, MathTransform transform) {
/*  85 */     super(definition, sourceCRS, targetCRS, transform);
/*  86 */     this.method = definition.getMethod();
/*     */   }
/*     */   
/*     */   public DefaultOperation(Map<String, ?> properties, CoordinateReferenceSystem sourceCRS, CoordinateReferenceSystem targetCRS, MathTransform transform, OperationMethod method) {
/* 106 */     super(properties, sourceCRS, targetCRS, transform);
/* 107 */     ensureNonNull("method", method);
/* 108 */     DefaultOperationMethod.checkDimensions(method, transform);
/* 109 */     this.method = method;
/*     */   }
/*     */   
/*     */   public static CoordinateOperation create(Map<String, ?> properties, CoordinateReferenceSystem sourceCRS, CoordinateReferenceSystem targetCRS, MathTransform transform, OperationMethod method, Class<? extends CoordinateOperation> type) {
/* 136 */     if (method != null) {
/* 137 */       if (method instanceof MathTransformProvider) {
/* 138 */         Class<? extends Operation> candidate = ((MathTransformProvider)method).getOperationType();
/* 140 */         if (candidate != null && (
/* 141 */           type == null || type.isAssignableFrom(candidate)))
/* 142 */           type = candidate.asSubclass((Class)type); 
/*     */       } 
/* 146 */       if (type != null) {
/* 147 */         if (Transformation.class.isAssignableFrom(type))
/* 148 */           return new DefaultTransformation(properties, sourceCRS, targetCRS, transform, method); 
/* 151 */         if (ConicProjection.class.isAssignableFrom(type))
/* 152 */           return new DefaultConicProjection(properties, sourceCRS, targetCRS, transform, method); 
/* 155 */         if (CylindricalProjection.class.isAssignableFrom(type))
/* 156 */           return new DefaultCylindricalProjection(properties, sourceCRS, targetCRS, transform, method); 
/* 159 */         if (PlanarProjection.class.isAssignableFrom(type))
/* 160 */           return new DefaultPlanarProjection(properties, sourceCRS, targetCRS, transform, method); 
/* 163 */         if (Projection.class.isAssignableFrom(type))
/* 164 */           return new DefaultProjection(properties, sourceCRS, targetCRS, transform, method); 
/* 167 */         if (Conversion.class.isAssignableFrom(type))
/* 168 */           return new DefaultConversion(properties, sourceCRS, targetCRS, transform, method); 
/*     */       } 
/* 172 */       return new DefaultOperation(properties, sourceCRS, targetCRS, transform, method);
/*     */     } 
/* 175 */     return new DefaultSingleOperation(properties, sourceCRS, targetCRS, transform);
/*     */   }
/*     */   
/*     */   public OperationMethod getMethod() {
/* 182 */     return this.method;
/*     */   }
/*     */   
/*     */   public ParameterValueGroup getParameterValues() throws UnsupportedOperationException {
/* 196 */     return getParameterValues(this.transform, this.method.getParameters(), true);
/*     */   }
/*     */   
/*     */   private static ParameterValueGroup getParameterValues(MathTransform mt, ParameterDescriptorGroup descriptor, boolean required) {
/* 212 */     while (mt != null) {
/* 213 */       if (mt instanceof ConcatenatedTransform) {
/* 214 */         ConcatenatedTransform ct = (ConcatenatedTransform)mt;
/* 215 */         ParameterValueGroup param1 = getParameterValues(ct.transform1, descriptor, false);
/* 216 */         ParameterValueGroup param2 = getParameterValues(ct.transform2, descriptor, false);
/* 217 */         if (param1 == null && param2 != null)
/* 217 */           return param2; 
/* 218 */         if (param2 == null && param1 != null)
/* 218 */           return param1; 
/* 219 */         required = true;
/*     */       } 
/* 221 */       if (mt instanceof AbstractMathTransform) {
/* 222 */         ParameterValueGroup param = ((AbstractMathTransform)mt).getParameterValues();
/* 223 */         if (param != null)
/* 224 */           return param; 
/*     */       } 
/* 227 */       if (mt instanceof PassThroughTransform)
/* 228 */         mt = ((PassThroughTransform)mt).getSubTransform(); 
/*     */     } 
/* 233 */     if (required)
/* 234 */       throw new UnsupportedImplementationException(mt.getClass()); 
/* 236 */     return null;
/*     */   }
/*     */   
/*     */   public boolean equals(AbstractIdentifiedObject object, boolean compareMetadata) {
/* 251 */     if (super.equals(object, compareMetadata)) {
/* 252 */       DefaultOperation that = (DefaultOperation)object;
/* 253 */       if (compareMetadata)
/* 254 */         return equals((IdentifiedObject)this.method, (IdentifiedObject)that.method, compareMetadata); 
/* 287 */       return true;
/*     */     } 
/* 289 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 297 */     return super.hashCode() ^ this.method.hashCode();
/*     */   }
/*     */   
/*     */   protected String formatWKT(Formatter formatter) {
/* 305 */     String name = super.formatWKT(formatter);
/* 306 */     append(formatter, (IdentifiedObject)this.method, "METHOD");
/* 307 */     return name;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\operation\DefaultOperation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */