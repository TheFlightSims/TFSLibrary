/*     */ package org.geotools.referencing.crs;
/*     */ 
/*     */ import java.util.Map;
/*     */ import org.geotools.referencing.AbstractIdentifiedObject;
/*     */ import org.geotools.referencing.operation.DefaultConversion;
/*     */ import org.geotools.referencing.operation.DefaultOperation;
/*     */ import org.geotools.referencing.operation.DefaultOperationMethod;
/*     */ import org.geotools.referencing.wkt.Formatter;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ import org.opengis.geometry.MismatchedDimensionException;
/*     */ import org.opengis.referencing.IdentifiedObject;
/*     */ import org.opengis.referencing.crs.CoordinateReferenceSystem;
/*     */ import org.opengis.referencing.crs.GeneralDerivedCRS;
/*     */ import org.opengis.referencing.crs.SingleCRS;
/*     */ import org.opengis.referencing.cs.CoordinateSystem;
/*     */ import org.opengis.referencing.datum.Datum;
/*     */ import org.opengis.referencing.operation.Conversion;
/*     */ import org.opengis.referencing.operation.MathTransform;
/*     */ import org.opengis.referencing.operation.NoninvertibleTransformException;
/*     */ import org.opengis.referencing.operation.OperationMethod;
/*     */ import org.opengis.referencing.operation.Projection;
/*     */ 
/*     */ public class AbstractDerivedCRS extends AbstractSingleCRS implements GeneralDerivedCRS {
/*     */   private static final long serialVersionUID = -175151161496419854L;
/*     */   
/*     */   public static final String CONVERSION_TYPE_KEY = "conversionType";
/*     */   
/* 102 */   public static final ThreadLocal<Boolean> _COMPARING = new ThreadLocal<Boolean>();
/*     */   
/*     */   protected final CoordinateReferenceSystem baseCRS;
/*     */   
/*     */   protected final Conversion conversionFromBase;
/*     */   
/*     */   protected AbstractDerivedCRS(GeneralDerivedCRS crs) {
/* 126 */     super((SingleCRS)crs);
/* 127 */     this.baseCRS = crs.getBaseCRS();
/* 128 */     this.conversionFromBase = crs.getConversionFromBase();
/*     */   }
/*     */   
/*     */   protected AbstractDerivedCRS(Map<String, ?> properties, Conversion conversionFromBase, CoordinateReferenceSystem base, MathTransform baseToDerived, CoordinateSystem derivedCS) throws MismatchedDimensionException {
/* 155 */     super(properties, getDatum(base), derivedCS);
/* 156 */     ensureNonNull("conversionFromBase", conversionFromBase);
/* 157 */     ensureNonNull("baseToDerived", baseToDerived);
/* 158 */     this.baseCRS = base;
/* 159 */     checkDimensions(base, baseToDerived, derivedCS);
/* 160 */     DefaultOperationMethod.checkDimensions(conversionFromBase.getMethod(), baseToDerived);
/* 161 */     Class<?> c = (Class)properties.get("conversionType");
/* 162 */     Class<? extends Conversion> typeHint = getConversionType();
/* 163 */     if (c != null)
/* 164 */       typeHint = c.asSubclass((Class)typeHint); 
/* 166 */     this.conversionFromBase = DefaultConversion.create(conversionFromBase, base, this, baseToDerived, typeHint);
/*     */   }
/*     */   
/*     */   protected AbstractDerivedCRS(Map<String, ?> properties, CoordinateReferenceSystem base, MathTransform baseToDerived, CoordinateSystem derivedCS) throws MismatchedDimensionException {
/* 222 */     this(properties, (OperationMethod)new DefaultOperationMethod(baseToDerived), base, baseToDerived, derivedCS);
/*     */   }
/*     */   
/*     */   AbstractDerivedCRS(Map<String, ?> properties, OperationMethod method, CoordinateReferenceSystem base, MathTransform baseToDerived, CoordinateSystem derivedCS) throws MismatchedDimensionException {
/* 238 */     super(properties, getDatum(base), derivedCS);
/* 239 */     ensureNonNull("method", method);
/* 240 */     ensureNonNull("baseToDerived", baseToDerived);
/* 241 */     this.baseCRS = base;
/* 249 */     checkDimensions(base, baseToDerived, derivedCS);
/* 250 */     DefaultOperationMethod.checkDimensions(method, baseToDerived);
/* 251 */     this.conversionFromBase = (Conversion)DefaultOperation.create((Map)new UnprefixedMap(properties, "conversion."), base, this, baseToDerived, method, (this instanceof org.opengis.referencing.crs.ProjectedCRS) ? Projection.class : Conversion.class);
/*     */   }
/*     */   
/*     */   private static Datum getDatum(CoordinateReferenceSystem base) {
/* 267 */     ensureNonNull("base", base);
/* 268 */     return (base instanceof SingleCRS) ? ((SingleCRS)base).getDatum() : null;
/*     */   }
/*     */   
/*     */   private static void checkDimensions(CoordinateReferenceSystem base, MathTransform baseToDerived, CoordinateSystem derivedCS) throws MismatchedDimensionException {
/* 279 */     int dimSource = baseToDerived.getSourceDimensions();
/* 280 */     int dimTarget = baseToDerived.getTargetDimensions();
/*     */     int dim1, dim2;
/* 282 */     if ((dim1 = dimSource) != (dim2 = base.getCoordinateSystem().getDimension()) || (dim1 = dimTarget) != (dim2 = derivedCS.getDimension()))
/* 285 */       throw new MismatchedDimensionException(Errors.format(93, Integer.valueOf(dim1), Integer.valueOf(dim2))); 
/*     */   }
/*     */   
/*     */   public CoordinateReferenceSystem getBaseCRS() {
/* 296 */     return this.baseCRS;
/*     */   }
/*     */   
/*     */   public Conversion getConversionFromBase() {
/* 305 */     return this.conversionFromBase;
/*     */   }
/*     */   
/*     */   Class<? extends Conversion> getConversionType() {
/* 313 */     return Conversion.class;
/*     */   }
/*     */   
/*     */   public boolean equals(AbstractIdentifiedObject object, boolean compareMetadata) {
/* 326 */     if (object == this)
/* 327 */       return true; 
/* 329 */     if (super.equals(object, compareMetadata)) {
/* 330 */       AbstractDerivedCRS that = (AbstractDerivedCRS)object;
/* 331 */       if (equals((IdentifiedObject)this.baseCRS, (IdentifiedObject)that.baseCRS, compareMetadata)) {
/* 336 */         Boolean comparing = _COMPARING.get();
/* 337 */         if (comparing != null && comparing.booleanValue())
/* 338 */           return true; 
/*     */         try {
/* 341 */           _COMPARING.set(Boolean.TRUE);
/* 342 */           return equals((IdentifiedObject)this.conversionFromBase, (IdentifiedObject)that.conversionFromBase, compareMetadata);
/*     */         } finally {
/* 346 */           _COMPARING.remove();
/*     */         } 
/*     */       } 
/*     */     } 
/* 350 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 367 */     return 0x550241F2 ^ this.baseCRS.hashCode() ^ this.conversionFromBase.getName().hashCode();
/*     */   }
/*     */   
/*     */   protected String formatWKT(Formatter formatter) {
/* 380 */     MathTransform inverse = this.conversionFromBase.getMathTransform();
/*     */     try {
/* 382 */       inverse = inverse.inverse();
/* 383 */     } catch (NoninvertibleTransformException exception) {
/* 385 */       throw new IllegalStateException(exception.getLocalizedMessage(), exception);
/*     */     } 
/* 387 */     formatter.append(inverse);
/* 388 */     formatter.append((IdentifiedObject)this.baseCRS);
/* 389 */     return "FITTED_CS";
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\crs\AbstractDerivedCRS.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */