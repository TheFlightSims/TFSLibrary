/*     */ package org.geotools.referencing.operation;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import javax.measure.unit.SI;
/*     */ import javax.measure.unit.Unit;
/*     */ import org.geotools.metadata.iso.quality.PositionalAccuracyImpl;
/*     */ import org.geotools.referencing.AbstractIdentifiedObject;
/*     */ import org.geotools.referencing.crs.AbstractDerivedCRS;
/*     */ import org.geotools.referencing.wkt.Formatter;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ import org.geotools.util.Utilities;
/*     */ import org.opengis.metadata.extent.Extent;
/*     */ import org.opengis.metadata.quality.PositionalAccuracy;
/*     */ import org.opengis.metadata.quality.QuantitativeResult;
/*     */ import org.opengis.metadata.quality.Result;
/*     */ import org.opengis.referencing.IdentifiedObject;
/*     */ import org.opengis.referencing.crs.CoordinateReferenceSystem;
/*     */ import org.opengis.referencing.operation.ConcatenatedOperation;
/*     */ import org.opengis.referencing.operation.ConicProjection;
/*     */ import org.opengis.referencing.operation.Conversion;
/*     */ import org.opengis.referencing.operation.CoordinateOperation;
/*     */ import org.opengis.referencing.operation.CylindricalProjection;
/*     */ import org.opengis.referencing.operation.MathTransform;
/*     */ import org.opengis.referencing.operation.Operation;
/*     */ import org.opengis.referencing.operation.PlanarProjection;
/*     */ import org.opengis.referencing.operation.Projection;
/*     */ import org.opengis.referencing.operation.Transformation;
/*     */ import org.opengis.util.InternationalString;
/*     */ import org.opengis.util.Record;
/*     */ 
/*     */ public class AbstractCoordinateOperation extends AbstractIdentifiedObject implements CoordinateOperation {
/*     */   private static final long serialVersionUID = 1237358357729193885L;
/*     */   
/* 101 */   public static final PositionalAccuracy[] EMPTY_ACCURACY_ARRAY = new PositionalAccuracy[0];
/*     */   
/* 106 */   private static final String[] LOCALIZABLES = new String[] { "scope" };
/*     */   
/*     */   protected final CoordinateReferenceSystem sourceCRS;
/*     */   
/*     */   protected final CoordinateReferenceSystem targetCRS;
/*     */   
/*     */   final String operationVersion;
/*     */   
/*     */   private final Collection<PositionalAccuracy> coordinateOperationAccuracy;
/*     */   
/*     */   protected final Extent domainOfValidity;
/*     */   
/*     */   private final InternationalString scope;
/*     */   
/*     */   protected final MathTransform transform;
/*     */   
/*     */   AbstractCoordinateOperation(Conversion definition, CoordinateReferenceSystem sourceCRS, CoordinateReferenceSystem targetCRS, MathTransform transform) {
/* 156 */     super((IdentifiedObject)definition);
/* 157 */     this.sourceCRS = sourceCRS;
/* 158 */     this.targetCRS = targetCRS;
/* 159 */     this.operationVersion = definition.getOperationVersion();
/* 160 */     this.coordinateOperationAccuracy = definition.getCoordinateOperationAccuracy();
/* 161 */     this.domainOfValidity = definition.getDomainOfValidity();
/* 162 */     this.scope = definition.getScope();
/* 163 */     this.transform = transform;
/*     */   }
/*     */   
/*     */   public AbstractCoordinateOperation(Map<String, ?> properties, CoordinateReferenceSystem sourceCRS, CoordinateReferenceSystem targetCRS, MathTransform transform) {
/* 211 */     this(properties, new HashMap<String, Object>(), sourceCRS, targetCRS, transform);
/*     */   }
/*     */   
/*     */   private AbstractCoordinateOperation(Map<String, ?> properties, Map<String, Object> subProperties, CoordinateReferenceSystem sourceCRS, CoordinateReferenceSystem targetCRS, MathTransform transform) {
/* 224 */     super(properties, subProperties, LOCALIZABLES);
/* 226 */     this.domainOfValidity = (Extent)subProperties.get("domainOfValidity");
/* 227 */     this.scope = (InternationalString)subProperties.get("scope");
/* 228 */     this.operationVersion = (String)subProperties.get("operationVersion");
/* 229 */     PositionalAccuracy[] positionalAccuracy = (PositionalAccuracy[])subProperties.get("coordinateOperationAccuracy");
/* 230 */     if (positionalAccuracy == null || positionalAccuracy.length == 0) {
/* 231 */       positionalAccuracy = null;
/*     */     } else {
/* 233 */       positionalAccuracy = (PositionalAccuracy[])positionalAccuracy.clone();
/* 234 */       for (int i = 0; i < positionalAccuracy.length; i++)
/* 235 */         ensureNonNull("coordinateOperationAccuracy", (Object[])positionalAccuracy, i); 
/*     */     } 
/* 238 */     this.coordinateOperationAccuracy = asSet((Object[])positionalAccuracy);
/* 239 */     this.sourceCRS = sourceCRS;
/* 240 */     this.targetCRS = targetCRS;
/* 241 */     this.transform = transform;
/* 242 */     validate();
/*     */   }
/*     */   
/*     */   void validate() throws IllegalArgumentException {
/* 261 */     ensureNonNull("sourceCRS", this.transform);
/* 262 */     ensureNonNull("targetCRS", this.transform);
/* 263 */     ensureNonNull("transform", this.transform);
/* 264 */     checkDimension("sourceCRS", this.sourceCRS, this.transform.getSourceDimensions());
/* 265 */     checkDimension("targetCRS", this.targetCRS, this.transform.getTargetDimensions());
/*     */   }
/*     */   
/*     */   private static void checkDimension(String name, CoordinateReferenceSystem crs, int expected) {
/* 279 */     int actual = crs.getCoordinateSystem().getDimension();
/* 280 */     if (actual != expected)
/* 281 */       throw new IllegalArgumentException(Errors.format(94, name, Integer.valueOf(actual), Integer.valueOf(expected))); 
/*     */   }
/*     */   
/*     */   public CoordinateReferenceSystem getSourceCRS() {
/* 290 */     return this.sourceCRS;
/*     */   }
/*     */   
/*     */   public CoordinateReferenceSystem getTargetCRS() {
/* 297 */     return this.targetCRS;
/*     */   }
/*     */   
/*     */   public String getOperationVersion() {
/* 308 */     return this.operationVersion;
/*     */   }
/*     */   
/*     */   public Collection<PositionalAccuracy> getCoordinateOperationAccuracy() {
/* 323 */     if (this.coordinateOperationAccuracy == null)
/* 324 */       return Collections.emptySet(); 
/* 326 */     return this.coordinateOperationAccuracy;
/*     */   }
/*     */   
/*     */   public Collection<PositionalAccuracy> getPositionalAccuracy() {
/* 341 */     if (this.coordinateOperationAccuracy == null)
/* 342 */       return Collections.emptySet(); 
/* 344 */     return this.coordinateOperationAccuracy;
/*     */   }
/*     */   
/*     */   public double getAccuracy() {
/* 357 */     return getAccuracy0(this);
/*     */   }
/*     */   
/*     */   public static double getAccuracy(CoordinateOperation operation) {
/* 393 */     if (operation instanceof AbstractCoordinateOperation)
/* 395 */       return ((AbstractCoordinateOperation)operation).getAccuracy(); 
/* 397 */     return getAccuracy0(operation);
/*     */   }
/*     */   
/*     */   private static double getAccuracy0(CoordinateOperation operation) {
/* 407 */     Collection<PositionalAccuracy> accuracies = operation.getCoordinateOperationAccuracy();
/* 408 */     if (accuracies != null)
/* 408 */       for (PositionalAccuracy positionalAccuracy : accuracies) {
/* 409 */         if (positionalAccuracy != null)
/* 409 */           for (Result result : positionalAccuracy.getResults()) {
/* 410 */             if (result instanceof QuantitativeResult) {
/* 411 */               QuantitativeResult quantity = (QuantitativeResult)result;
/* 412 */               Collection<? extends Record> records = quantity.getValues();
/* 413 */               if (records != null) {
/* 414 */                 Unit<?> unit = quantity.getValueUnit();
/* 415 */                 if (unit != null && SI.METER.isCompatible(unit))
/* 416 */                   for (Record record : records) {
/* 417 */                     for (Object value : record.getAttributes().values()) {
/* 418 */                       if (value instanceof Number) {
/* 419 */                         double v = ((Number)value).doubleValue();
/* 420 */                         v = unit.getConverterTo(SI.METER).convert(v);
/* 421 */                         return v;
/*     */                       } 
/*     */                     } 
/*     */                   }  
/*     */               } 
/*     */             } 
/*     */           }  
/*     */       }  
/* 434 */     if (operation instanceof Conversion)
/* 435 */       return 0.0D; 
/* 442 */     if (operation instanceof Transformation) {
/* 443 */       if (!accuracies.contains(PositionalAccuracyImpl.DATUM_SHIFT_OMITTED) && 
/* 444 */         accuracies.contains(PositionalAccuracyImpl.DATUM_SHIFT_APPLIED))
/* 445 */         return 25.0D; 
/* 448 */       return 1000.0D;
/*     */     } 
/* 454 */     double accuracy = Double.NaN;
/* 455 */     if (operation instanceof ConcatenatedOperation) {
/* 456 */       Collection components = ((ConcatenatedOperation)operation).getOperations();
/* 457 */       for (Iterator<CoordinateOperation> it = components.iterator(); it.hasNext(); ) {
/* 458 */         double candidate = Math.abs(getAccuracy(it.next()));
/* 459 */         if (!Double.isNaN(candidate)) {
/* 460 */           if (Double.isNaN(accuracy)) {
/* 461 */             accuracy = candidate;
/*     */             continue;
/*     */           } 
/* 463 */           accuracy += candidate;
/*     */         } 
/*     */       } 
/*     */     } 
/* 468 */     return accuracy;
/*     */   }
/*     */   
/*     */   public Extent getDomainOfValidity() {
/* 478 */     return this.domainOfValidity;
/*     */   }
/*     */   
/*     */   public Extent getValidArea() {
/* 489 */     return this.domainOfValidity;
/*     */   }
/*     */   
/*     */   public InternationalString getScope() {
/* 496 */     return this.scope;
/*     */   }
/*     */   
/*     */   public MathTransform getMathTransform() {
/* 505 */     return this.transform;
/*     */   }
/*     */   
/*     */   public static Class<? extends CoordinateOperation> getType(CoordinateOperation object) {
/* 516 */     if (object instanceof Transformation)
/* 516 */       return (Class)Transformation.class; 
/* 517 */     if (object instanceof ConicProjection)
/* 517 */       return (Class)ConicProjection.class; 
/* 518 */     if (object instanceof CylindricalProjection)
/* 518 */       return (Class)CylindricalProjection.class; 
/* 519 */     if (object instanceof PlanarProjection)
/* 519 */       return (Class)PlanarProjection.class; 
/* 520 */     if (object instanceof Projection)
/* 520 */       return (Class)Projection.class; 
/* 521 */     if (object instanceof Conversion)
/* 521 */       return (Class)Conversion.class; 
/* 522 */     if (object instanceof Operation)
/* 522 */       return (Class)Operation.class; 
/* 523 */     return CoordinateOperation.class;
/*     */   }
/*     */   
/*     */   public boolean equals(AbstractIdentifiedObject object, boolean compareMetadata) {
/* 539 */     if (object == this)
/* 540 */       return true; 
/* 542 */     if (super.equals(object, compareMetadata)) {
/* 543 */       AbstractCoordinateOperation that = (AbstractCoordinateOperation)object;
/* 544 */       if (equals((IdentifiedObject)this.sourceCRS, (IdentifiedObject)that.sourceCRS, compareMetadata) && Utilities.equals(this.transform, that.transform)) {
/* 548 */         if (compareMetadata && (
/* 549 */           !Utilities.equals(this.domainOfValidity, that.domainOfValidity) || !Utilities.equals(this.scope, that.scope) || !Utilities.equals(this.coordinateOperationAccuracy, that.coordinateOperationAccuracy)))
/* 553 */           return false; 
/* 560 */         Boolean comparing = AbstractDerivedCRS._COMPARING.get();
/* 561 */         if (comparing != null && comparing.booleanValue())
/* 562 */           return true; 
/*     */         try {
/* 565 */           AbstractDerivedCRS._COMPARING.set(Boolean.TRUE);
/* 566 */           return equals((IdentifiedObject)this.targetCRS, (IdentifiedObject)that.targetCRS, compareMetadata);
/*     */         } finally {
/* 568 */           AbstractDerivedCRS._COMPARING.set(Boolean.FALSE);
/*     */         } 
/*     */       } 
/*     */     } 
/* 572 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 580 */     int code = -598790243;
/* 581 */     if (this.sourceCRS != null)
/* 581 */       code ^= this.sourceCRS.hashCode(); 
/* 582 */     if (this.targetCRS != null)
/* 582 */       code ^= this.targetCRS.hashCode(); 
/* 583 */     if (this.transform != null)
/* 583 */       code ^= this.transform.hashCode(); 
/* 584 */     return code;
/*     */   }
/*     */   
/*     */   protected String formatWKT(Formatter formatter) {
/* 597 */     append(formatter, (IdentifiedObject)this.sourceCRS, "SOURCE");
/* 598 */     append(formatter, (IdentifiedObject)this.targetCRS, "TARGET");
/* 599 */     return super.formatWKT(formatter);
/*     */   }
/*     */   
/*     */   static void append(Formatter formatter, IdentifiedObject object, final String type) {
/* 612 */     if (object != null) {
/* 613 */       Map<String, Object> properties = new HashMap<String, Object>(4);
/* 614 */       properties.put("name", formatter.getName(object));
/* 615 */       properties.put("identifiers", formatter.getIdentifier(object));
/* 616 */       formatter.append((IdentifiedObject)new AbstractIdentifiedObject(properties) {
/*     */             protected String formatWKT(Formatter formatter) {
/* 624 */               return type;
/*     */             }
/*     */           });
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\operation\AbstractCoordinateOperation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */