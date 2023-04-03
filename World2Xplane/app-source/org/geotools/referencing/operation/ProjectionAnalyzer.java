/*     */ package org.geotools.referencing.operation;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.LogRecord;
/*     */ import java.util.logging.Logger;
/*     */ import javax.measure.unit.SI;
/*     */ import javax.measure.unit.Unit;
/*     */ import org.geotools.referencing.AbstractIdentifiedObject;
/*     */ import org.geotools.referencing.CRS;
/*     */ import org.geotools.referencing.cs.AbstractCS;
/*     */ import org.geotools.referencing.factory.ReferencingFactory;
/*     */ import org.geotools.referencing.operation.matrix.MatrixFactory;
/*     */ import org.geotools.referencing.operation.matrix.XMatrix;
/*     */ import org.geotools.referencing.operation.transform.AbstractMathTransform;
/*     */ import org.geotools.referencing.operation.transform.ConcatenatedTransform;
/*     */ import org.geotools.resources.i18n.Loggings;
/*     */ import org.geotools.util.Utilities;
/*     */ import org.opengis.parameter.GeneralParameterValue;
/*     */ import org.opengis.parameter.ParameterDescriptor;
/*     */ import org.opengis.parameter.ParameterDescriptorGroup;
/*     */ import org.opengis.parameter.ParameterValue;
/*     */ import org.opengis.parameter.ParameterValueGroup;
/*     */ import org.opengis.referencing.IdentifiedObject;
/*     */ import org.opengis.referencing.crs.ProjectedCRS;
/*     */ import org.opengis.referencing.cs.CartesianCS;
/*     */ import org.opengis.referencing.cs.CoordinateSystem;
/*     */ import org.opengis.referencing.operation.Conversion;
/*     */ import org.opengis.referencing.operation.MathTransform;
/*     */ import org.opengis.referencing.operation.Matrix;
/*     */ 
/*     */ final class ProjectionAnalyzer {
/*     */   private final Conversion projection;
/*     */   
/*     */   private final Matrix geographicScale;
/*     */   
/*     */   private final Matrix projectedScale;
/*     */   
/*     */   private final MathTransform transform;
/*     */   
/*     */   private List<GeneralParameterValue> parameters;
/*     */   
/*     */   private ProjectionAnalyzer(ProjectedCRS crs) {
/* 121 */     Matrix geographicScale = null;
/* 122 */     Matrix projectedScale = null;
/* 123 */     this.projection = (Conversion)crs.getConversionFromBase();
/* 124 */     MathTransform candidate = this.projection.getMathTransform();
/* 125 */     while (candidate instanceof ConcatenatedTransform) {
/* 126 */       ConcatenatedTransform ctr = (ConcatenatedTransform)candidate;
/* 127 */       if (ctr.transform1 instanceof LinearTransform) {
/* 128 */         if (geographicScale != null)
/* 130 */           throw new IllegalStateException(String.valueOf(candidate)); 
/* 132 */         geographicScale = ((LinearTransform)ctr.transform1).getMatrix();
/* 133 */         candidate = ctr.transform2;
/*     */         continue;
/*     */       } 
/* 136 */       if (ctr.transform2 instanceof LinearTransform) {
/* 137 */         if (projectedScale != null)
/* 139 */           throw new IllegalStateException(String.valueOf(candidate)); 
/* 141 */         projectedScale = ((LinearTransform)ctr.transform2).getMatrix();
/* 142 */         candidate = ctr.transform1;
/*     */         continue;
/*     */       } 
/* 146 */       candidate = null;
/*     */     } 
/* 161 */     ParameterValueGroup group = null;
/* 162 */     if (candidate instanceof AbstractMathTransform)
/* 163 */       group = ((AbstractMathTransform)candidate).getParameterValues(); 
/* 165 */     if (group == null) {
/* 177 */       group = this.projection.getParameterValues();
/* 178 */       if (projectedScale == null) {
/* 179 */         CartesianCS cartesianCS = crs.getCoordinateSystem();
/* 180 */         projectedScale = AbstractCS.swapAndScaleAxis(AbstractCS.standard((CoordinateSystem)cartesianCS), (CoordinateSystem)cartesianCS);
/*     */       } 
/*     */     } 
/* 183 */     if (group != null)
/* 184 */       this.parameters = group.values(); 
/* 186 */     this.geographicScale = geographicScale;
/* 187 */     this.projectedScale = projectedScale;
/* 188 */     this.transform = candidate;
/*     */   }
/*     */   
/*     */   private ParameterDescriptorGroup getTransformDescriptor() {
/* 195 */     return (this.transform instanceof AbstractMathTransform) ? ((AbstractMathTransform)this.transform).getParameterDescriptors() : null;
/*     */   }
/*     */   
/*     */   private XMatrix normalizedToProjection() {
/* 212 */     this.parameters = new LinkedList<GeneralParameterValue>(this.parameters);
/* 218 */     int sourceDim = (this.transform != null) ? this.transform.getTargetDimensions() : 2;
/* 219 */     int targetDim = (this.projectedScale != null) ? (this.projectedScale.getNumCol() - 1) : sourceDim;
/* 220 */     XMatrix matrix = MatrixFactory.create(targetDim + 1, sourceDim + 1);
/* 238 */     Unit<?> unit = null;
/* 239 */     String warning = null;
/* 240 */     for (Iterator<GeneralParameterValue> it = this.parameters.iterator(); it.hasNext(); ) {
/* 241 */       GeneralParameterValue parameter = it.next();
/* 242 */       if (parameter instanceof ParameterValue) {
/* 243 */         ParameterValue<?> value = (ParameterValue)parameter;
/* 244 */         ParameterDescriptor<?> descriptor = value.getDescriptor();
/* 245 */         if (Number.class.isAssignableFrom(descriptor.getValueClass())) {
/* 246 */           if (AbstractIdentifiedObject.nameMatches((IdentifiedObject)descriptor, "scale_factor")) {
/* 247 */             double scale = value.doubleValue();
/* 248 */             for (int i = Math.min(sourceDim, targetDim); --i >= 0;)
/* 249 */               matrix.setElement(i, i, matrix.getElement(i, i) * scale); 
/*     */           } else {
/*     */             int d;
/* 253 */             if (AbstractIdentifiedObject.nameMatches((IdentifiedObject)descriptor, "false_easting")) {
/* 254 */               d = 0;
/* 255 */             } else if (AbstractIdentifiedObject.nameMatches((IdentifiedObject)descriptor, "false_northing")) {
/* 256 */               d = 1;
/*     */             } else {
/*     */               continue;
/*     */             } 
/* 260 */             double offset = value.doubleValue(SI.METER);
/* 261 */             if (!Double.isNaN(offset) && offset != value.doubleValue()) {
/* 265 */               unit = value.getUnit();
/* 266 */               warning = descriptor.getName().getCode();
/*     */             } 
/* 268 */             matrix.setElement(d, sourceDim, matrix.getElement(d, sourceDim) + offset);
/*     */           } 
/* 270 */           it.remove();
/*     */         } 
/*     */       } 
/*     */     } 
/* 274 */     if (warning != null) {
/* 275 */       LogRecord record = Loggings.format(Level.WARNING, 4, warning, unit, SI.METER);
/* 277 */       record.setSourceClassName(getClass().getName());
/* 278 */       record.setSourceMethodName("createLinearConversion");
/* 279 */       Logger logger = ReferencingFactory.LOGGER;
/* 280 */       record.setLoggerName(logger.getName());
/* 281 */       logger.log(record);
/*     */     } 
/* 283 */     return matrix;
/*     */   }
/*     */   
/*     */   private static boolean parameterValuesEqual(List<GeneralParameterValue> source, List<GeneralParameterValue> target, double errorTolerance) {
/* 295 */     for (Iterator<GeneralParameterValue> targetIter = target.iterator(); targetIter.hasNext(); ) {
/* 296 */       GeneralParameterValue targetPrm = targetIter.next();
/*     */       Iterator<GeneralParameterValue> sourceIter;
/* 297 */       for (sourceIter = source.iterator(); sourceIter.hasNext(); ) {
/* 298 */         GeneralParameterValue sourcePrm = sourceIter.next();
/* 299 */         if (!AbstractIdentifiedObject.nameMatches((IdentifiedObject)sourcePrm.getDescriptor(), (IdentifiedObject)targetPrm.getDescriptor()))
/*     */           continue; 
/* 302 */         if (sourcePrm instanceof ParameterValue) {
/* 302 */           if (targetPrm instanceof ParameterValue) {
/* 303 */             ParameterValue<?> sourceValue = (ParameterValue)sourcePrm;
/* 304 */             ParameterValue<?> targetValue = (ParameterValue)targetPrm;
/* 305 */             if (Number.class.isAssignableFrom(targetValue.getDescriptor().getValueClass())) {
/*     */               double sourceNum, targetNum;
/* 307 */               Unit<?> unit = targetValue.getUnit();
/* 308 */               if (unit != null) {
/* 309 */                 sourceNum = sourceValue.doubleValue(unit);
/* 310 */                 targetNum = targetValue.doubleValue(unit);
/*     */               } else {
/* 312 */                 sourceNum = sourceValue.doubleValue();
/* 313 */                 targetNum = targetValue.doubleValue();
/*     */               } 
/* 315 */               double error = targetNum - sourceNum;
/* 316 */               if (targetNum != 0.0D)
/* 316 */                 error /= targetNum; 
/* 317 */               if (Math.abs(error) > errorTolerance)
/* 318 */                 return false; 
/*     */               continue;
/*     */             } 
/* 323 */             if (!Utilities.equals(sourceValue.getValue(), targetValue.getValue()))
/* 324 */               return false; 
/*     */           } else {
/*     */             continue;
/*     */           } 
/* 330 */         } else if (!Utilities.equals(targetPrm, sourcePrm)) {
/* 331 */           return false;
/*     */         } 
/* 336 */         sourceIter.remove();
/* 337 */         targetIter.remove();
/*     */       } 
/* 343 */       return false;
/*     */     } 
/* 347 */     assert target.isEmpty();
/* 348 */     return source.isEmpty();
/*     */   }
/*     */   
/*     */   private XMatrix applyProjectedScale(XMatrix normalizedToProjection) {
/* 355 */     if (this.projectedScale == null)
/* 356 */       return normalizedToProjection; 
/* 358 */     XMatrix scale = MatrixFactory.create(this.projectedScale);
/* 359 */     scale.multiply((Matrix)normalizedToProjection);
/* 360 */     return scale;
/*     */   }
/*     */   
/*     */   public static Matrix createLinearConversion(ProjectedCRS sourceCRS, ProjectedCRS targetCRS, double errorTolerance) {
/* 390 */     if (!CRS.equalsIgnoreMetadata(sourceCRS.getDatum(), targetCRS.getDatum()))
/* 391 */       return null; 
/* 393 */     ProjectionAnalyzer source = new ProjectionAnalyzer(sourceCRS);
/* 394 */     ProjectionAnalyzer target = new ProjectionAnalyzer(targetCRS);
/* 395 */     if (!AbstractIdentifiedObject.nameMatches((IdentifiedObject)source.projection.getMethod(), (IdentifiedObject)target.projection.getMethod())) {
/* 414 */       ParameterDescriptorGroup sourceDsc = source.getTransformDescriptor();
/* 415 */       ParameterDescriptorGroup targetDsc = source.getTransformDescriptor();
/* 416 */       if (sourceDsc == null || targetDsc == null || !AbstractIdentifiedObject.nameMatches((IdentifiedObject)sourceDsc, (IdentifiedObject)targetDsc))
/* 417 */         return null; 
/*     */     } 
/* 424 */     if (source.parameters == null || target.parameters == null)
/* 425 */       return null; 
/* 427 */     XMatrix sourceScale = source.normalizedToProjection();
/* 428 */     XMatrix targetScale = target.normalizedToProjection();
/* 429 */     if (!parameterValuesEqual(source.parameters, target.parameters, errorTolerance))
/* 430 */       return null; 
/* 442 */     targetScale = target.applyProjectedScale(targetScale);
/* 443 */     sourceScale = source.applyProjectedScale(sourceScale);
/* 444 */     sourceScale.invert();
/* 445 */     targetScale.multiply((Matrix)sourceScale);
/* 446 */     if (targetScale.isIdentity(errorTolerance))
/* 447 */       targetScale.setIdentity(); 
/* 449 */     return (Matrix)targetScale;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\operation\ProjectionAnalyzer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */