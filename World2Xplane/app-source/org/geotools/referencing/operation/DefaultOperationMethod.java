/*     */ package org.geotools.referencing.operation;
/*     */ 
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import org.geotools.parameter.Parameters;
/*     */ import org.geotools.referencing.AbstractIdentifiedObject;
/*     */ import org.geotools.referencing.operation.transform.AbstractMathTransform;
/*     */ import org.geotools.referencing.operation.transform.ConcatenatedTransform;
/*     */ import org.geotools.referencing.operation.transform.PassThroughTransform;
/*     */ import org.geotools.referencing.wkt.Formatter;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ import org.geotools.resources.i18n.Vocabulary;
/*     */ import org.geotools.util.Utilities;
/*     */ import org.opengis.geometry.MismatchedDimensionException;
/*     */ import org.opengis.parameter.ParameterDescriptorGroup;
/*     */ import org.opengis.referencing.IdentifiedObject;
/*     */ import org.opengis.referencing.operation.MathTransform;
/*     */ import org.opengis.referencing.operation.Matrix;
/*     */ import org.opengis.referencing.operation.Operation;
/*     */ import org.opengis.referencing.operation.OperationMethod;
/*     */ import org.opengis.referencing.operation.Projection;
/*     */ import org.opengis.util.InternationalString;
/*     */ 
/*     */ public class DefaultOperationMethod extends AbstractIdentifiedObject implements OperationMethod {
/*     */   private static final long serialVersionUID = -98032729598205972L;
/*     */   
/*  71 */   private static final String[] LOCALIZABLES = new String[] { "formula" };
/*     */   
/*     */   private final InternationalString formula;
/*     */   
/*     */   protected final int sourceDimensions;
/*     */   
/*     */   protected final int targetDimensions;
/*     */   
/*     */   private final ParameterDescriptorGroup parameters;
/*     */   
/*     */   public DefaultOperationMethod(MathTransform transform) {
/* 103 */     this(getProperties(transform), transform.getSourceDimensions(), transform.getTargetDimensions(), getDescriptor(transform));
/*     */   }
/*     */   
/*     */   private static Map<String, ?> getProperties(MathTransform transform) {
/*     */     Map<String, ?> properties;
/* 114 */     ensureNonNull("transform", transform);
/* 116 */     if (transform instanceof AbstractMathTransform) {
/* 117 */       AbstractMathTransform mt = (AbstractMathTransform)transform;
/* 118 */       properties = getProperties((IdentifiedObject)mt.getParameterDescriptors(), null);
/*     */     } else {
/* 120 */       properties = Collections.singletonMap("name", Vocabulary.format(252));
/*     */     } 
/* 122 */     return properties;
/*     */   }
/*     */   
/*     */   private static ParameterDescriptorGroup getDescriptor(MathTransform transform) {
/* 131 */     ParameterDescriptorGroup descriptor = null;
/* 132 */     if (transform instanceof AbstractMathTransform)
/* 133 */       descriptor = ((AbstractMathTransform)transform).getParameterDescriptors(); 
/* 135 */     return descriptor;
/*     */   }
/*     */   
/*     */   public DefaultOperationMethod(OperationMethod method) {
/* 148 */     super((IdentifiedObject)method);
/* 149 */     this.formula = method.getFormula();
/* 150 */     this.parameters = method.getParameters();
/* 151 */     this.sourceDimensions = method.getSourceDimensions();
/* 152 */     this.targetDimensions = method.getTargetDimensions();
/*     */   }
/*     */   
/*     */   public DefaultOperationMethod(OperationMethod method, int sourceDimensions, int targetDimensions) {
/* 167 */     super((IdentifiedObject)method);
/* 168 */     this.formula = method.getFormula();
/* 169 */     this.parameters = method.getParameters();
/* 170 */     this.sourceDimensions = sourceDimensions;
/* 171 */     this.targetDimensions = targetDimensions;
/* 172 */     ensurePositive("sourceDimensions", sourceDimensions);
/* 173 */     ensurePositive("targetDimensions", targetDimensions);
/*     */   }
/*     */   
/*     */   public DefaultOperationMethod(Map<String, ?> properties, int sourceDimensions, int targetDimensions, ParameterDescriptorGroup parameters) {
/* 205 */     this(properties, new HashMap<String, Object>(), sourceDimensions, targetDimensions, parameters);
/*     */   }
/*     */   
/*     */   private DefaultOperationMethod(Map<String, ?> properties, Map<String, Object> subProperties, int sourceDimensions, int targetDimensions, ParameterDescriptorGroup parameters) {
/* 218 */     super(properties, subProperties, LOCALIZABLES);
/* 219 */     this.formula = (InternationalString)subProperties.get("formula");
/* 222 */     this.parameters = parameters;
/* 223 */     this.sourceDimensions = sourceDimensions;
/* 224 */     this.targetDimensions = targetDimensions;
/* 225 */     ensurePositive("sourceDimensions", sourceDimensions);
/* 226 */     ensurePositive("targetDimensions", targetDimensions);
/*     */   }
/*     */   
/*     */   private static void ensurePositive(String name, int value) throws IllegalArgumentException {
/* 240 */     if (value < 0)
/* 241 */       throw new IllegalArgumentException(Errors.format(58, name, Integer.valueOf(value))); 
/*     */   }
/*     */   
/*     */   public InternationalString getFormula() {
/* 252 */     return this.formula;
/*     */   }
/*     */   
/*     */   public int getSourceDimensions() {
/* 261 */     return this.sourceDimensions;
/*     */   }
/*     */   
/*     */   public int getTargetDimensions() {
/* 268 */     return this.targetDimensions;
/*     */   }
/*     */   
/*     */   public ParameterDescriptorGroup getParameters() {
/* 275 */     return (this.parameters != null) ? this.parameters : Parameters.EMPTY_GROUP;
/*     */   }
/*     */   
/*     */   Class<? extends Operation> getOperationType() {
/* 287 */     return (Class)Projection.class;
/*     */   }
/*     */   
/*     */   public boolean equals(AbstractIdentifiedObject object, boolean compareMetadata) {
/* 302 */     if (object == this)
/* 303 */       return true; 
/* 305 */     if (super.equals(object, compareMetadata)) {
/* 306 */       DefaultOperationMethod that = (DefaultOperationMethod)object;
/* 307 */       if (this.sourceDimensions == that.sourceDimensions && this.targetDimensions == that.targetDimensions && equals((IdentifiedObject)this.parameters, (IdentifiedObject)that.parameters, compareMetadata))
/* 311 */         return (!compareMetadata || Utilities.equals(this.formula, that.formula)); 
/*     */     } 
/* 314 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 322 */     int code = 2012209132 + this.sourceDimensions + 37 * this.targetDimensions;
/* 323 */     if (this.parameters != null)
/* 324 */       code = code * 37 + this.parameters.hashCode(); 
/* 326 */     return code;
/*     */   }
/*     */   
/*     */   protected String formatWKT(Formatter formatter) {
/* 339 */     if (Projection.class.isAssignableFrom(getOperationType()))
/* 340 */       return "PROJECTION"; 
/* 342 */     return super.formatWKT(formatter);
/*     */   }
/*     */   
/*     */   private static boolean isTrivial(MathTransform transform) {
/* 352 */     if (transform instanceof LinearTransform) {
/* 353 */       Matrix matrix = ((LinearTransform)transform).getMatrix();
/* 354 */       int size = matrix.getNumRow();
/* 355 */       if (matrix.getNumCol() == size) {
/* 356 */         for (int j = 0; j < size; j++) {
/* 357 */           int n1 = 0, n2 = 0;
/* 358 */           for (int i = 0; i < size; i++) {
/* 359 */             if (matrix.getElement(j, i) != 0.0D)
/* 359 */               n1++; 
/* 360 */             if (matrix.getElement(i, j) != 0.0D)
/* 360 */               n2++; 
/*     */           } 
/* 362 */           if (n1 != 1 || n2 != 1)
/* 363 */             return false; 
/*     */         } 
/* 366 */         return true;
/*     */       } 
/*     */     } 
/* 369 */     return false;
/*     */   }
/*     */   
/*     */   public static void checkDimensions(OperationMethod method, MathTransform transform) throws MismatchedDimensionException {
/* 390 */     if (method != null && transform != null) {
/*     */       String name;
/* 391 */       int expected = method.getSourceDimensions();
/*     */       int actual;
/* 392 */       while ((actual = transform.getSourceDimensions()) > expected) {
/* 393 */         if (transform instanceof ConcatenatedTransform) {
/* 395 */           ConcatenatedTransform c = (ConcatenatedTransform)transform;
/* 396 */           if (isTrivial(c.transform1)) {
/* 397 */             transform = c.transform2;
/*     */             continue;
/*     */           } 
/* 398 */           if (isTrivial(c.transform2)) {
/* 399 */             transform = c.transform1;
/*     */             continue;
/*     */           } 
/*     */           break;
/*     */         } 
/* 406 */         if (transform instanceof PassThroughTransform)
/* 407 */           transform = ((PassThroughTransform)transform).getSubTransform(); 
/*     */       } 
/* 413 */       if (actual != expected) {
/* 414 */         name = "sourceDimensions";
/*     */       } else {
/* 416 */         actual = transform.getTargetDimensions();
/* 417 */         expected = method.getTargetDimensions();
/* 418 */         if (actual != expected) {
/* 419 */           name = "targetDimensions";
/*     */         } else {
/*     */           return;
/*     */         } 
/*     */       } 
/* 424 */       throw new IllegalArgumentException(Errors.format(94, name, Integer.valueOf(actual), Integer.valueOf(expected)));
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\operation\DefaultOperationMethod.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */